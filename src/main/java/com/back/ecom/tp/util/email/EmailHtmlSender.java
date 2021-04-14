package com.back.ecom.tp.util.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;

@Service
@RequiredArgsConstructor
public class EmailHtmlSender {

    @Autowired
    private EmailSender emailSender;
    @Autowired
    private TemplateEngine templateEngine;


    @Async
    public void send(String to, String subject, String templateName, Context context, File attachment) {
        String body = templateEngine.process(templateName, context);
        emailSender.sendHtml(to, subject, body,attachment);
    }
}
