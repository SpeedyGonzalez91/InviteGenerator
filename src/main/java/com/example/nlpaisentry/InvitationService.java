package com.example.nlpaisentry;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InvitationService {


    OpenAIService openAIService;

    EmailService emailService;



    public InvitationService(OpenAIService openAIService, EmailService emailService) {
        this.openAIService = openAIService;
        this.emailService = emailService;
    }

    public boolean sendEmail(String emailAddress, String invitationContent) {
        // Modify this method to use your email service to send the invitation email
        return emailService.sendEmail(emailAddress, invitationContent);
    }
}


