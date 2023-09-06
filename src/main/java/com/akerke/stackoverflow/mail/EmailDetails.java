package com.akerke.stackoverflow.mail;

public record EmailDetails (
     String recipient,
     String msgBody,
     String subject,
     String attachment){

}