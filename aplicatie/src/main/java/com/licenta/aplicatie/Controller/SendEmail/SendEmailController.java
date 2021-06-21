package com.licenta.aplicatie.Controller.SendEmail;

import com.licenta.aplicatie.Models.SituatieScolara.Eveniment;
import com.licenta.aplicatie.Models.Utils.Email;
import com.licenta.aplicatie.Service.Email.EmailService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/licenta/sendEmail")
public class SendEmailController {

    @Autowired
    private EmailService sendEmailService;
    private static final String QR_CODE_IMAGE_PATH = "aplicatie/src/main/resources/QRCodes/QRCode";

    @CrossOrigin
    @RequestMapping(value = "/email={emailTo}", method = {RequestMethod.POST})
    public ResponseEntity<?> getEvenimentByDisciplina(@PathVariable("emailTo") String emailTo,@RequestBody Email email) {
        try {
            sendEmailService.sendEmail(emailTo,email.getSubject(),email.getText());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
//    @CrossOrigin
//    @RequestMapping(value = "/attachment/email={emailTo}", method = {RequestMethod.POST})
//    public ResponseEntity<?> sendEmailWithAttachment(@PathVariable("emailTo") String emailTo) {
//        try {
//            sendEmailService.sendEmailWithAttachment(emailTo,QR_CODE_IMAGE_PATH+"_Marketing_Sosea Sorina_laborator_12.png",emailTo);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (Exception ex) {
//            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }
}
