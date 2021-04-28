package com.licenta.aplicatie.Controller.SendEmail;

import com.licenta.aplicatie.Service.Email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/licenta/sendEmail")
public class SendEmailController {

    @Autowired
    private EmailService sendEmailService;

    @CrossOrigin
    @RequestMapping(value = "/email={emailTo}", method = {RequestMethod.POST})
    public ResponseEntity<?> getEvenimentByDisciplina(@PathVariable("emailTo") String emailTo) {
        try {
            sendEmailService.sendEmail(emailTo);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
