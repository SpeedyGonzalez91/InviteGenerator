package com.example.nlpaisentry;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    JavaMailSender javaMailSender;




    public boolean sendEmail(String emailAddress, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailAddress);
            message.setSubject("Automated Invitation");
            message.setText(content);

            javaMailSender.send(message);

            return true; // Email sent successfully
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Email sending failed
        }
    }

}