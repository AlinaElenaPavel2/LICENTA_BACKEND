package com.licenta.aplicatie.Service.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.*;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.mail.*;
import org.springframework.mail.javamail.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String emailAdress,String subject,String text) throws MessagingException {
//        SimpleMailMessage msg = new SimpleMailMessage();
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false);
        helper.setTo(emailAdress);
        helper.setSubject(subject);
        helper.setText(text);
        javaMailSender.send(message);

    }

    public void sendEmailWithAttachment(String emailAdress,String pathname,String studentName,Integer laborator,String materie) throws MessagingException, IOException {
        //--------------- TRYING ANOTHER WAY

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(emailAdress);
        helper.setSubject("Testing from Spring Boot");

        helper.setSubject("Validare prezenta - laborator "+laborator+" - "+materie);
        helper.setText(
                "<html>"
                        + "<body>"
                        + "<div>"+studentName+","
                        + "<div style=\"margin-top:20px;\"><strong>Pentru a valida prezenta pentru laboratorul "+laborator+" de "+materie+" scaneaza codul QR atasat! </strong></div>"
                        + "<div>"
                        + "<img src='cid:rightSideImage' style='float:right;width:150px;height:150px;'/>"
                        +"<br>"
                        +"<div style=\"color:red\">Timpul de scanarae a codului este 10 min din momemntul in care ati primit emailul!</div>"
                        +"<br>"
                        +"<div>In cazul in care nu a fost scanat codul QR in intervalul mentionat mai sus se veti avea absent pentru laboratorul curent. </div>"
                        + "</div></body>"
                        + "</html>", true);
        helper.addInline("rightSideImage",
                new File(pathname));

        javaMailSender.send(message);

    }

    String generateContentId(String prefix) {
        return String.format("%s-%s", prefix, UUID.randomUUID());
    }
}

