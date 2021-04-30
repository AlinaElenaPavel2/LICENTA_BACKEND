package com.licenta.aplicatie.Service.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String emailAdress) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailAdress);

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);

    }

    public void sendEmailWithAttachment(String emailAdress) throws MessagingException, IOException {
//--------------- IT WORKS BUT THE IMEGE SHOULD BE UPLOAD O, NOT LOCAL
//        MimeMessage message = javaMailSender.createMimeMessage();
//
//        // true = multipart message
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//        helper.setTo(emailAdress);
//
//        helper.setSubject("Testing from Spring Boot");
//
//        // default = text/plain
//        //helper.setText("Check attachment for image!");
//
//        // true = text/html
////        helper.setText("<h1>Check attachment for image!</h1>", true);
//        message.setContent
//                ("<h1>This is a test</h1>"
//                                + "<img src=\"https://www.linkpicture.com/q/QRCode.png\">",
//                        "text/html");//        // hardcoded a file path
//        FileSystemResource file = new FileSystemResource(new File("aplicatie/src/main/resources/QRCodes/QRCode.png"));
//
//        helper.addAttachment("QRCode.png", file);
//
//        javaMailSender.send(message);

        //--------------- TRYING ANOTHER WAY

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(emailAdress);
        helper.setSubject("Testing from Spring Boot");

        helper.setSubject("Email with Inline images Example");
        helper.setText(
                "<html>"
                        + "<body>"
                        + "<div>Dear student,"
                        + "<div><strong>Add the image to the right:</strong></div>"
                        + "<div>"
                        + "<img src='cid:rightSideImage' style='float:right;width:150px;height:150px;'/>"
                        + "<div>Adding a inline resource/image on to the right of the paragraph.</div>"
                        + "<div>Adding a inline resource/image on to the right of the paragraph.</div>"
                        + "<div>Adding a inline resource/image on to the right of the paragraph.</div>"
                        + "<div>Adding a inline resource/image on to the right of the paragraph.</div>"
                        + "</div>"
                        + "<div>Thanks,</div>"
                        + "kalliphant"
                        + "</div></body>"
                        + "</html>", true);
        helper.addInline("rightSideImage",
                new File("D:/LICENTA/BACKEND/LICENTA_BACKEND/aplicatie/src/main/resources/QRCodes/QRCode.png"));

        javaMailSender.send(message);

    }

    String generateContentId(String prefix) {
        return String.format("%s-%s", prefix, UUID.randomUUID());
    }
}

