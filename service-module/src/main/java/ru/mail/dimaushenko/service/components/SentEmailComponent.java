package ru.mail.dimaushenko.service.components;

public interface SentEmailComponent {

    boolean sentMessage(String to, String subject, String message);

}
