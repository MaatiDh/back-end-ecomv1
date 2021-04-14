package com.back.ecom.tp.util.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;


@Log4j2
@Component
@RequiredArgsConstructor
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromPersonal;

    @Value("${spring.mail.from.address}")
    private String fromAddress;

    public void sendHtml(String to, String subject, String htmlBody, File attachment) {
        sendEmail(to, subject, htmlBody, attachment);
    }

    private void sendEmail(String to, String subject, String text, File attachment) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            helper.setSentDate(new Date());
            helper.setFrom(new InternetAddress(fromAddress, fromPersonal));
            if (attachment != null) {
                helper.addAttachment(attachment.getName(),attachment);
            }
            javaMailSender.send(mail);
        } catch (Exception e) {
            log.error("send mail error: " + e.getLocalizedMessage());
        }
    }
}
