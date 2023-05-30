package ma.digency.gov.amc.process;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.configuration.SecurityConstants;
import ma.digency.gov.amc.dto.*;
import ma.digency.gov.amc.dto.shared.ResetDoneRequest;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.mapper.AccountMapper;
import ma.digency.gov.amc.mapper.AuthentificationMapper;
import ma.digency.gov.amc.repository.entity.proposalproject.ProjectSubDomain;
import ma.digency.gov.amc.repository.entity.shared.*;
import ma.digency.gov.amc.service.AccountService;
import ma.digency.gov.amc.service.shared.AuthIssueService;
import ma.digency.gov.amc.service.shared.HostService;
import ma.digency.gov.amc.service.shared.NotificationTemplateService;
import ma.digency.gov.amc.service.shared.RoleService;
import ma.digency.gov.amc.utils.AmcUtilis;
import ma.digency.gov.amc.utils.AuthenticationThirdParty;
import ma.digency.gov.amc.utils.LoadResourceFile;
import ma.digency.gov.amc.utils.enumeration.*;
import ma.digency.gov.amc.utils.service.OneTimePasswordService;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import ma.digency.gov.amc.utils.service.SendMailSMSService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountProcessImpl implements AccountProcess {
    private final String placeholderName="user.txt";

    private final String placeholderPath="placeholders/";

    private static Logger logger = Logger.getLogger(AccountProcess.class.getName());

    private final AccountService accountService;

    private final RoleService roleService;

    private final HostService hostService;

    private final AccountMapper accountMapper;

    private final AuthenticationThirdParty authenticationThirdParty;

    private final NotificationTemplateService notificationTemplateService;

    private final SendMailSMSService notificationService;

    private final ReferenceSequenceService referenceSequenceService;

    private final OneTimePasswordService oneTimePasswordService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final LoadResourceFile loadResourceFile;

    private final AuthIssueService authIssueService;

    @Async
    public void registerThirdParty(AccountRequest accountDto)
    {
        AuthIssue issue = new AuthIssue();
        issue.setError(null);
        issue.setAuthActionType(AuthActionType.REGISTER);
        issue.setEmail(accountDto.getEmail());
        try{
            UserDtoResponse response = authenticationThirdParty.createUser(
                    thirdPartyRegister(accountDto)
            );
            Boolean status = Boolean.parseBoolean(response.getStatus());
            if(!status)
                issue.setError(response.getMessage());
        }catch (Exception e)
        {
            issue.setError(e.getMessage());
        }
        finally {
            if(issue.getError()!=null)
            {
                logger.warning("Creating auth issue for "+issue.getEmail()+" with reason "+issue.getError()
                +" while "+issue.getAuthActionType());
                authIssueService.createIssue(issue);
            }
        }
    }

    @Override
    @Transactional
    public void createPersonAccount(AccountRequest accountDto) {
        if(!accountDto.isMail()) {
            accountDto.setStatus(AccountEnum.UNVERIFIED);
            accountDto.setVkey(AmcUtilis.randomString(50));
            accountService.checkPersonAccountExist(accountDto.getEmail(), accountDto.getPhoneNumber(), accountDto.getCin());
           /* String key = accountDto.getPhoneNumber();
            if (accountDto.isMail()) {
                key = accountDto.getEmail();
            }
            oneTimePasswordService.checkGenerateOtp(key, accountDto.getOtpCode());*/
            registerThirdParty(accountDto);
            Role role = roleService.findRoleByCodeRole("USER");
            Account addAccount = accountMapper.accountRequestDtoToAccount(accountDto);
            addAccount.setRefAccount(referenceSequenceService.generateRefAccount());
            addAccount.setPassword(bCryptPasswordEncoder.encode(addAccount.getPassword()));
            RoleAccount accountRole = new RoleAccount(new AccountRoleId(role.getRefRole(), addAccount.getRefAccount()), role);
            if (CollectionUtils.isEmpty(addAccount.getRoles())) {
                addAccount.setRoles(Set.of(accountRole));
            } else {
                addAccount.getRoles().add(accountRole);
            }
            String photo = loadResourceFile.loadFile(placeholderPath,placeholderName);
            addAccount.setPhoto(photo.getBytes());
            accountService.createNewAccount(addAccount);
            try{
                if(!accountDto.isDoNotSendEmail()) {
                    TemplateNotification notificationTemplate = notificationTemplateService
                            .findTemplateNotificationByNatureAndType(NotificationType.MAIL, NotificationNature.CONFIRM_EMAIL);
                    String templateBody = loadResourceFile.loadFile(notificationTemplate.getTemplateBody())
                            .replace("#link", hostService.getHostName() + "/account/verify/" + addAccount.getVkey() + "?redirectTo=" +
                                    URLEncoder.encode(accountDto.getRedirectTo(), java.nio.charset.StandardCharsets.UTF_8.toString()))
                            .replace("#name", addAccount.getLastname().toUpperCase() + " " + addAccount.getFirstname());
                    notificationService.sendMail(addAccount.getEmail(), notificationTemplate.getTemplateObject(), templateBody);
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }else{
            var ac = accountService.findAccountByEmail(accountDto.getEmail());
            if(ac.isEmpty()){
                throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACCOUNT_NOT_FOUND);
            }
            ac.get().setLogin(accountDto.getLogin());
            accountService.updateAccount(ac.get());

        }


    }

    @Override
    public void generateOTPCode(AccountRequest authRequest) {
        //accountService.checkPersonAccountExist(authRequest.getEmail(), authRequest.getPhoneNumber(), );
        //template Type
        NotificationType notificationType = NotificationType.SMS;
        String sendTo = authRequest.getPhoneNumber();
        if (authRequest.isMail()) {
            notificationType = NotificationType.MAIL;
            sendTo = authRequest.getEmail();
        }
        String codeOtp = oneTimePasswordService.generateOTP(sendTo);
        //find TemplateNotification
        TemplateNotification notificationTemplate = notificationTemplateService
                .findTemplateNotificationByNatureAndType(notificationType, NotificationNature.GENERATION_OTP);

        //prepare templateBody
        String templateBody = notificationTemplate.getTemplateBody().replace("#otp", codeOtp);
        //send otp code
        //notificationService.sendNotification(sendTo, notificationTemplate.getTemplateObject(),templateBody,notificationType);

    }

    @Override
    public String getInformation(String user) {
        Optional<Account> account = accountService.findAccountByEmail(user);
        if(account.isPresent())
            return  account.get().getLogin();
        return "none";
    }

    @Override
    public void updateAccountStatusByVkey(String vkey, AccountEnum status) {
        Account account = accountService.findAccountByVkey(vkey).orElseThrow(
                ()->{ throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACCOUNT_NOT_FOUND);}
        );
        this.accountService.generateNewVkey(account);
        this.accountService.updateAccountStatus(account,status);
        try{
            TemplateNotification notificationTemplate = notificationTemplateService
                    .findTemplateNotificationByNatureAndType(NotificationType.MAIL, NotificationNature.CONFIRM_EMAIL_DONE);

            String templateBody = loadResourceFile.loadFile(notificationTemplate.getTemplateBody());
            notificationService.sendMail(account.getEmail(), notificationTemplate.getTemplateObject(),templateBody);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void sendResetRequest(String email, String redirectTo) {
        Optional<Account> accountTmp = accountService.findAccountByEmail(email);
        if(accountTmp.isEmpty())
            return;
        Account account = accountTmp.get();
        try{
            TemplateNotification notificationTemplate = notificationTemplateService
                    .findTemplateNotificationByNatureAndType(NotificationType.MAIL, NotificationNature.RESET_PASSWORD);
            String templateBody = loadResourceFile.loadFile(notificationTemplate.getTemplateBody())
                    .replace("#link", hostService.getHostName()+"/account/reset/"+account.getVkey()+"?redirectTo="+
                            URLEncoder.encode(redirectTo,java.nio.charset.StandardCharsets.UTF_8.toString()))
                    .replace("#name", account.getLastname().toUpperCase()+" "+ account.getFirstname());
            notificationService.sendMail(account.getEmail(), notificationTemplate.getTemplateObject(),templateBody);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_MAIL_FAILED);
        }
    }

    @Override
    public void resetPassword(ResetDoneRequest resetDoneRequest) {
        Optional<Account> accountTmp = this.accountService.findAccountByVkey(resetDoneRequest.getVkey());
        Account account = accountTmp.orElseThrow(
                ()->{
                    throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_VKEY_EXPIRED);
                }
        );
        account.setPassword(bCryptPasswordEncoder.encode(resetDoneRequest.getPassword()));
        accountService.updateAccount(account);
        accountService.generateNewVkey(account);
        try{
            TemplateNotification notificationTemplate = notificationTemplateService
                    .findTemplateNotificationByNatureAndType(NotificationType.MAIL, NotificationNature.PASSWORD_CHANGED);
            String templateBody = loadResourceFile.loadFile(notificationTemplate.getTemplateBody());
            notificationService.sendMail(account.getEmail(), notificationTemplate.getTemplateObject(),templateBody);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkRequest(String vkey) {
        if(accountService.findAccountByVkey(vkey).isPresent())
            return true;
        return false;
    }

    @Override
    public UpdateProfileResponse updateProfile(UpdateProfileRequest request) {
        String emailInRequest = request.getEmail();
        Account account = accountService.getAccountInRequest();
        if(account==null)
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACCOUNT_NOT_FOUND);
        String oldEmail = account.getEmail();
        Optional<Account> checking = accountService.findAccountByEmail(emailInRequest);
        if(checking.isPresent()&&!emailInRequest.equals(account.getEmail()))
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EMAIL_IN_USE);
        account = accountMapper.updateAccountDto(request,account);
        accountService.updateAccount(account);
        UpdateProfileResponse out = accountMapper.accountToUpdateProfileDto(account);
        if(!emailInRequest.equals(oldEmail))
            out.setToken(this.refreshToken(account));
        return out;
    }

    @Override
    public String uploadProfilePhoto(MultipartFile photo) {
        Account account = accountService.getAccountInRequest();
        if(account==null)
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACCOUNT_NOT_FOUND);
        try{
            account.setPhoto(Base64.encodeBase64(photo.getBytes()));
        }catch (IOException e) {
            e.printStackTrace();
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_TECHNICAL_ERROR);
        }
        accountService.updateAccount(account);
        return accountService.getPhoto(account);
    }

    @Override
    public void updatePassword(ChangePasswordRequest request) {
        Account account = accountService.getAccountInRequest();
        if(account==null)
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACCOUNT_NOT_FOUND);
        if(!request.getNewPassword().equals(request.getNewPasswordConfirmed()))
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_PASSWORD_NOT_CONFIRMED);
        if(!bCryptPasswordEncoder.matches(request.getOldPassword(),account.getPassword()))
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_OLD_PASSWORD_INVALID);
        account.setPassword(bCryptPasswordEncoder.encode(request.getNewPassword()));
        accountService.updateAccount(account);
    }

    @Override
    public void updateAccountStatus(String refAccount, AccountEnum status) {
        Account account = accountService.findAccountByRefAccount(refAccount);
        accountService.updateAccountStatus(account,status);
    }

    @Override
    public void blockAccount(String refAccount) {
        this.updateAccountStatus(refAccount,AccountEnum.DELETED);
    }

    @Override
    public void updateAccount(AccountRequest request, String refAccount) {
        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        Account account = accountService.findAccountByRefAccount(refAccount);
        Optional<Account> check = accountService.findAccountByEmail(request.getEmail());
        if(check.isPresent()&&!check.get().getRefAccount().equals(account.getRefAccount()))
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EMAIL_IN_USE);
        account = accountMapper.updateAccountRequest(request,account);
        accountService.updateAccount(account);
    }

    @Override
    public void assignAccountToRoles(AssignRolesRequest request) {
        Account account = accountService.findAccountByRefAccount(request.getRefAccount());
        this.accountService.removeAllPreviousRoles(account);
        account.getRoles().removeAll(account.getRoles());
        List<Role> roles = new ArrayList<>();
        for(String refRole : request.getRoles())
        {
            roles.add(roleService.findRoleByCodeRole(refRole));
        }
        List<RoleAccount> list = new ArrayList<>();
        for(Role role : roles)
        {
            RoleAccount accountRole = new RoleAccount(new AccountRoleId(role.getRefRole(), account.getRefAccount()), role);
            if (CollectionUtils.isEmpty(account.getRoles())) {
                Set set = new HashSet();
                set.add(accountRole);
                account.setRoles(set);
            } else {
                account.getRoles().add(accountRole);
            }
        }
        accountService.updateAccount(account);
    }


    @Override
    public PageableResponse<AccountAdminResponse> findAllAccountPageable(Pageable pageable) {
        return new PageableResponse<>(accountService.findAllPageable(pageable).map(this.accountMapper::accountToAccountAdminDto));
    }

    @Override
    public AccountAdminResponse findAccountByRef(String refAccount) {
        Account account = accountService.findAccountByRefAccount(refAccount);
        return accountMapper.accountToAccountAdminDto(account);
    }

    @Override
    public List<String> getRolesByUser(String refAccount) {
        Account account = accountService.findAccountByRefAccount(refAccount);
        return account.getRoles().stream().map(e->{return e.getRole().getCodeRole();})
                .collect(Collectors.toList());
    }

    @Autowired
    private HttpServletRequest request;

    private final String refreshToken(Account account)
    {
        String[] roles = new String[account.getRoles().size()];
        int i=0;
        for(RoleAccount roleAccount : account.getRoles())
        {
            roles[i]=roleAccount.getRole().getCodeRole();
            i++;
        }
        String jwtToken = JWT.create()
                .withIssuer(request.getRequestURI())
                .withSubject(account.getEmail())
                .withArrayClaim("roles", roles)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SecurityConstants.SECRET));
        return jwtToken;
    }

    private final UserDtoRequest thirdPartyRegister(AccountRequest accountRequest)
    {
        UserDtoRequest out = new UserDtoRequest();
        out.set_minor(false);
        out.set_resident(true);
        out.setBirthday(new Date());
        out.setEmail(accountRequest.getEmail());
        out.setFirstName(accountRequest.getFirstname());
        out.setLastName(accountRequest.getLastname());
        out.setCompany_name("");
        out.setParent_id_card("");
        out.setPhone(accountRequest.getPhoneNumber());
        out.setPassword(accountRequest.getPassword());
        out.setType(accountRequest.getAccountType().getLabel());
        if(accountRequest.getAccountType().equals(AccountType.PERSON))
        {
            out.setId_card(accountRequest.getCin());
            out.setTax_id("");
        }
        else
        {
            out.setTax_id(accountRequest.getCin());
            out.setId_card("");
        }
        return out;
    }
}
