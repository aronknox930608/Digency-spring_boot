package ma.digency.gov.amc.utils.service;

import ma.digency.gov.amc.utils.LoadResourceFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SendMailSMSService {

    private static final String FOOTER_TEMPLATE = "template-footer.txt";
    private static final String HEADER_TEMPLATE = "template-header.txt";
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private LoadResourceFile loadResourceFile;

    @Value("${spring.mail.username}")
    private String from;

    public void sendMailNonAsync(String to, String subject, String message) {
        try {
            String headerTemplate = loadResourceFile.loadFile(HEADER_TEMPLATE);
            String footerTemplate = loadResourceFile.loadFile(FOOTER_TEMPLATE);
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            String content = headerTemplate.concat(message).concat(footerTemplate);
            helper.setText(content, true);
            javaMailSender.send(msg);
        } catch (MessagingException e) {
            //TODO add lign on the DB to show the reason why the main was not sended
            e.printStackTrace();
        }
    }

    @Async
    public void sendMail(String to, String subject, String message) {
        sendMailNonAsync(to,subject,message);
    }

    public void sendSMS(String to, String subject, String message) {

    }

}
