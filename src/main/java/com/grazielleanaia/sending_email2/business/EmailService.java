package com.grazielleanaia.sending_email2.business;


import com.grazielleanaia.sending_email2.business.dto.TasksDTO;
import com.grazielleanaia.sending_email2.infrastructure.exception.EmailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor

public class EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Value("${send.email.remetent}")

    private String remetent;

    @Value("${send.email.remetentName}")
    private String remetentName;

    public void sendEmail(TasksDTO tasksDTO) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
            mimeMessageHelper.setFrom(new InternetAddress(remetent, remetentName));
            mimeMessageHelper.setTo(InternetAddress.parse(tasksDTO.getCustomerEmail()));
            mimeMessageHelper.setSubject("Task notification");

            Context context = new Context();
            context.setVariable("taskName", tasksDTO.getTaskName());
            context.setVariable("eventDate", tasksDTO.getEventDate());
            context.setVariable("description", tasksDTO.getDescription());

            String template = templateEngine.process("notification", context);
            mimeMessageHelper.setText(template, true);
            javaMailSender.send(message);

        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new EmailException("Error to send email", e.getCause());
        }

    }
}

