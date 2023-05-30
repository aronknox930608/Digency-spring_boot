package ma.digency.gov.amc.process;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.CommissionVoteRequest;
import ma.digency.gov.amc.dto.shared.*;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.mapper.CommissionMapper;
import ma.digency.gov.amc.repository.entity.shared.*;
import ma.digency.gov.amc.repository.entity.siel.Publication;
import ma.digency.gov.amc.service.AccountService;
import ma.digency.gov.amc.service.CommissionService;
import ma.digency.gov.amc.service.siel.PublicationService;
import ma.digency.gov.amc.utils.PatchUtils;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommissionProcessImpl implements CommissionProcess {

    private final CommissionService commissionService;

    private final CommissionMapper commissionMapper;

    private final AccountService accountService;

    private final ReferenceSequenceService referenceSequenceService;

    private final PublicationService publicationService;

    public static <T> Predicate<T> distinctByKey(final Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    @Override
    public CommissionResponse createCommission(CommissionRequest request) {
        Commission commission = commissionMapper.commissionRequestToCommission(request);
        commission.setRefCommission(referenceSequenceService.generateRefCommission());
        commission.getPlannings().forEach(planning -> {
            planning.setRefCommission(commission.getRefCommission());
            planning.setRefPlanning(referenceSequenceService.generateRefPlanningCommission());
        });
        return commissionMapper.commissionToCommissionResponse(commissionService.createCommission(commission));
    }

    @Override
    public List<CommissionResponse> findAllCommission() {
        return commissionService.findAllCommission().stream().map(commissionMapper::commissionToCommissionResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CommissionResponse updateCommission(String refCommission, CommissionResponse request) {
        Commission updateCommission = commissionService.findCommissionByRefCommission(refCommission);
        List<PlanningResponse> repo =
                commissionMapper.planningsToPlanningsResponse(updateCommission.getPlannings());
        commissionMapper.updateCommission(request, updateCommission);

        List<PlanningResponse> publicationAdd = PatchUtils.getObjectToBeAdd(repo, request.getPlannings());
        preparePlanningToBeAdd(updateCommission, publicationAdd);

        List<PlanningResponse> publicationUpdate =
                PatchUtils.getObjectToBeUpdated(repo, request.getPlannings());
        preparePlanningToBeUpdated(updateCommission, publicationUpdate);

        return commissionMapper.commissionToCommissionResponse(commissionService.updateCommission(updateCommission));
    }

    @Transactional
    @Override
    public List<CommissionMemberResponse> createCommissionMembers(String refCommission,
                                                                  List<CommissionMemberRequest> members) {
        Commission findCommission = commissionService.findCommissionByRefCommission(refCommission);

        //check for duplicate member login/phone/email
        checkDuplicateMember(members);

        prepareCommissionMemberToAdd(findCommission, members);

        commissionService.updateCommission(findCommission);
        return Collections.emptyList();
    }

    @Override
    public List<CommissionMemberResponse> findAllCommissionMembers() {
        return commissionService.findAllCommissionMembers().stream()
                .map(commissionMapper::commissionMemberToCommissionMemberResponse).collect(Collectors.toList());
    }

    @Override
    public void addCommissionMembers(String refCommission, List<String> refMembers) {
        //check double refMembers
        checkDuplicateRefCommissionMember(refMembers);
        //get the commission
        Commission commission = commissionService.findCommissionByRefCommission(refCommission);
        //check if any new refMember already existe in commission
        checkExistingMemberInCommission(commission, refMembers);
        //check if refMembers existe in data base
        List<CommissionAccount> commissionAccount = commissionService.checkExistingCommissionMembers(refMembers);
        //create relation between accountCommission and commission
        commissionAccount.forEach(account -> {
            CommissionMember commissionMember =
                    new CommissionMember(refCommission, account.getRefCommissionAccount(), account);
            commission.getMembers().add(commissionMember);
        });
        //update the commission
        commissionService.updateCommission(commission);
    }

    @Override
    public List<CommissionMemberResponse> findCommissionMembers(String refCommission) {
        Commission commission = commissionService.findCommissionByRefCommission(refCommission);

        return commission.getMembers().stream().map(commissionMapper::commissionMemberToCommissionMemberResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addPublicationsToTreat(String refCommission, String refPlanning, ReferenceRequest refs) {
        Commission commission = commissionService.findCommissionByRefCommission(refCommission);
        Planning planning = commission.getPlannings().stream()
                .filter(plg -> plg.getRefPlanning().equals(refPlanning))
                .findFirst()
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_PLANNING_NOT_FOUND));
        if (CollectionUtils.isEmpty(planning.getVotes())) {
            List<Vote> votes = new ArrayList<>();
            for (String ref : refs.getRefs()) {
                publicationService.findByRefPublication(ref);
                Vote vote = new Vote();
                vote.setRefPlanning(refPlanning);
                vote.setRefPublication(ref);
                vote.setAccepted(0);
                vote.setRejected(0);
                vote.setRefVote(referenceSequenceService.generateRefVoteCommission());
                votes.add(vote);
            }
            planning.getVotes().clear();
            planning.getVotes().addAll(votes);
            commissionService.updateCommission(commission);
        }

    }

    @Override
    public CommissionResponse findCommission(String refCommission) {
        Commission commission = commissionService.findCommissionByRefCommission(refCommission);
        return commissionMapper.commissionToCommissionResponse(commission);
    }

    @Override
    public void addVotePublication(String refCommission, CommissionVoteRequest voteRequest, String refPlanning) {
        Commission commission = commissionService.findCommissionByRefCommission(refCommission);
        Planning findPlanning = commission.getPlannings().stream()
                .filter(planning -> planning.getRefPlanning().equals(refPlanning))
                .findFirst()
                .orElseThrow(()-> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_PLANNING_NOT_FOUND));

        publicationService.findByRefPublication(voteRequest.getRefPublication());

        Vote vote = new Vote();
        vote.setRefPlanning(findPlanning.getRefPlanning());
        vote.setRefPublication(voteRequest.getRefPublication());
        vote.setAccepted(voteRequest.getNbrAccepted());
        vote.setRejected(voteRequest.getNbrDenied());
        vote.setRejectedReason(voteRequest.getDeniedReason());
        vote.setRefVote(referenceSequenceService.generateRefVoteCommission());

        findPlanning.getVotes().add(vote);
        commissionService.updateCommission(commission);
        publicationService.updateStatus(voteRequest.getRefPublication(),voteRequest.getStatus());


    }

    protected void prepareCommissionMemberToAdd(Commission findCommission, List<CommissionMemberRequest> members) {
        List<CommissionAccount> commissionAccounts =
                members.stream().map(commissionMapper::commissionMemberToCommissionAccount)
                        .collect(Collectors.toList());
        for (CommissionAccount account : commissionAccounts) {
            account.getAccount().setRefAccount(referenceSequenceService.generateRefAccount());
            account.getAccount().setPassword(RandomStringUtils.randomAlphanumeric(8));
            accountService.createNewAccount(account.getAccount());
            account.setRefCommissionAccount(referenceSequenceService.generateRefCommissionAccount());
            CommissionMember addMember = new CommissionMember();
            addMember.setRefCommission(findCommission.getRefCommission());
            addMember.setRefCommissionAccount(account.getRefCommissionAccount());
            addMember.setCommissionAccount(account);
            findCommission.getMembers().add(addMember);
        }
    }

    protected void preparePlanningToBeAdd(Commission updateCommission, List<PlanningResponse> planningAdd) {
        if (!CollectionUtils.isEmpty(planningAdd)) {
            planningAdd.forEach(planningDto -> {
                Planning planningToAdd = commissionMapper.planningResponseToPlanning(planningDto);
                planningToAdd.setRefCommission(updateCommission.getRefCommission());
                planningToAdd.setRefPlanning(referenceSequenceService.generateRefPlanningCommission());

                updateCommission.getPlannings().add(planningToAdd);
            });
        }
    }

    protected void preparePlanningToBeUpdated(Commission updateCommission, List<PlanningResponse> publicationUpdate) {
        if (!CollectionUtils.isEmpty(publicationUpdate) && !CollectionUtils.isEmpty(
                updateCommission.getPlannings())) {
            Map<String, Planning> publicationMap = updateCommission.getPlannings().stream()
                    .collect(Collectors.toMap(Planning::getRefPlanning, planning -> planning));
            publicationUpdate.forEach(planningDto -> {
                if (publicationMap.get(planningDto.getRefPlanning()) != null) {
                    commissionMapper.updatePlanning(planningDto, publicationMap.get(planningDto.getRefPlanning()));
                }
            });
        }
    }

    protected void checkDuplicateRefCommissionMember(List<String> refs) {
        if (CollectionUtils.isEmpty(refs)) {
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_INVALID_PARAMETER);
        }
        Set<String> duplicate = new HashSet<>(refs);
        if (duplicate.size() != refs.size()) {
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_DUPLICATE_MEMBER);
        }
    }

    protected void checkExistingMemberInCommission(Commission commission, List<String> refMembers) {
        if (!CollectionUtils.isEmpty(commission.getMembers())) {
            Map<String, CommissionAccount> map = commission.getMembers().stream().collect(
                    Collectors.toMap(CommissionMember::getRefCommissionAccount,
                            CommissionMember::getCommissionAccount));
            refMembers.forEach(ref -> {
                if (map.get(ref) != null) {
                    throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_DUPLICATE_MEMBER);
                }
            });
        }
    }

    protected void checkDuplicateMember(List<CommissionMemberRequest> members) {
        if (!CollectionUtils.isEmpty(members)) {
            // check by cin
            var mapCin = members.stream().filter(distinctByKey(CommissionMemberRequest::getCin))
                    .collect(Collectors.toList());
            if (mapCin.size() != members.size()) {
                throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_DUPLICATE_MEMBER);
            }
            //check by login
            var mapLogin = members.stream().filter(distinctByKey(CommissionMemberRequest::getLogin))
                    .collect(Collectors.toList());
            if (mapLogin.size() != members.size()) {
                throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_DUPLICATE_MEMBER);
            }
            // check by email
            var mapEmail = members.stream().filter(distinctByKey(CommissionMemberRequest::getEmail))
                    .collect(Collectors.toList());
            if (mapEmail.size() != members.size()) {
                throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_DUPLICATE_MEMBER);
            }
            // check by phoneNumber
            var mapPhone = members.stream().filter(distinctByKey(CommissionMemberRequest::getPhoneNumber))
                    .collect(Collectors.toList());
            if (mapPhone.size() != members.size()) {
                throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_DUPLICATE_MEMBER);
            }
        }

    }
}
