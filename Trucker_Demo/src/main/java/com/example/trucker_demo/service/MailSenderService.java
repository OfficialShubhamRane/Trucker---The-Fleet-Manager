package com.example.trucker_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    @Autowired
    JavaMailSender javaMailSender;

    /** Sends email */
    public void sendEmail(String body, String subject){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("from.springboot.gmail.com");
        message.setTo("shubham16.ranez@gmail.com");
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);

    }


}
