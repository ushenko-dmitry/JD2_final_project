package ru.mail.dimaushenko.service.utils.impl;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.service.utils.SentEmailUtil;

@Component
public class SentEmailUtilImpl implements SentEmailUtil {

    public final JavaMailSender javaMailSender;

    public SentEmailUtilImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public boolean sentMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        try {
            javaMailSender.send(message);
        } catch (MailException mailException) {
            return false;
        }
        return true;
    }

}
