package ru.mail.dimaushenko.service.utils;

public interface SentEmailUtil {

    boolean sentMessage(String to, String subject, String message);

}
