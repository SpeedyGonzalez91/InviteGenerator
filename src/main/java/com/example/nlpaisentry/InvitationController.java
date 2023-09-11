package com.example.nlpaisentry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/api")
public class InvitationController {

    @Autowired
    OpenAIService openAIService;

    @Autowired
    InvitationData invitationData;


    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("invitationData", new InvitationData("Name", "Instruction", "Location", "Reason"));
        return "index"; // The name of your HTML file for the form
    }

    @PostMapping("/generate-invitation")
    public ModelAndView generateInvitation(@RequestBody InvitationData invitationData) {
        try {
            String generatedInvitation = openAIService.generateInvitation(invitationData);

            // Create a ModelAndView to specify the view (HTML) and data to be passed
            ModelAndView modelAndView = new ModelAndView("invitation"); // The name of the HTML file to display the result (e.g., invitationResult.html)
            modelAndView.addObject("generatedInvitation", generatedInvitation);

            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("error"); // Handle errors with an error page (e.g., error.html)
        }

    }

}


