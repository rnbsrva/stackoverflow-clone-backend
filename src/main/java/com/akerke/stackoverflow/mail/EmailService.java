package com.akerke.stackoverflow.mail;

public interface EmailService {

    // Method
    String sendSimpleMail(EmailDetails details);

    String sendMailWithAttachment(EmailDetails details);
}
