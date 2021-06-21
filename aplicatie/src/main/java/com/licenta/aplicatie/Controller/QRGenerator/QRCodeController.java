package com.licenta.aplicatie.Controller.QRGenerator;

import com.google.zxing.WriterException;
import com.licenta.aplicatie.Models.SituatieScolara.Prezenta;
import com.licenta.aplicatie.Models.Users.Student;
import com.licenta.aplicatie.POJO.QRCodeGenerator;
import com.licenta.aplicatie.Service.Email.EmailService;
import com.licenta.aplicatie.Service.Users.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("api/licenta/qrCode")
public class QRCodeController {
    private static final String QR_CODE_IMAGE_PATH = "aplicatie/src/main/resources/QRCodes/QRCode";
    @Autowired
    StudentService studentService;
    @Autowired
    private EmailService sendEmailService;

    @CrossOrigin

    @RequestMapping(value = "/generate", method = {RequestMethod.POST})
    public void download(@RequestBody Prezenta prezenta) throws Exception {
        String prezentaToSTr = prezenta.toString();
        QRCodeGenerator.generateQRCodeImage(prezentaToSTr, 350, 350, QR_CODE_IMAGE_PATH + ".png");

    }


    @CrossOrigin
    @RequestMapping(value = "/generate/materie={materie}/grupa={grupa}/laborator={laborator}", method = {RequestMethod.POST})
    public ResponseEntity<?> download2(@PathVariable("materie") String materie, @PathVariable("grupa") String grupa, @PathVariable("laborator") Integer laborator) throws Exception {
        List<Student> studenti = studentService.findStudentsByGrupa(grupa);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        String[] arr = date.split(" ");
        String[] date_sub = arr[0].split("/");
        String data = date_sub[0] + "_" + date_sub[1] + "_" + date_sub[2];
//        for (Student student:studenti
//             ) {
//            String link = "http://localhost:4200/university/course/"+materie+"/student/"+student.getNume()+"/labo/rator/"+laborator+"/date/"+data+"/ora/"+arr[1]+"/present";
//            QRCodeGenerator.generateQRCodeImage(link, 650, 650, QR_CODE_IMAGE_PATH+"_"+materie+"_"+student.getNume()+"_laborator_"+laborator+".png");
//            sendEmailService.sendEmailWithAttachment(student.getEmail(),QR_CODE_IMAGE_PATH+"_"+materie+"_"+student.getNume()+"_laborator_"+laborator+".png");
//        }
        String link = "http://localhost:4200/university/course/" + materie + "/student/" + "Sosea Sorina" + "/laborator/" + laborator + "/date/" + data + "/ora/" + arr[1] + "/present";
        QRCodeGenerator.generateQRCodeImage(link, 650, 650, QR_CODE_IMAGE_PATH + "_" + materie + "_" + "Sosea Sorina" + "_laborator_" + laborator + ".png");
        sendEmailService.sendEmailWithAttachment("alina_pavel98@yahoo.com", QR_CODE_IMAGE_PATH + "_" + materie + "_" + "Sosea Sorina" + "_laborator_" + laborator + ".png","Sosea Sorina",laborator,materie);
        return new ResponseEntity<>(studenti, HttpStatus.OK);

    }

    @CrossOrigin
    @RequestMapping(value = "/", method = {RequestMethod.GET}, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> generateQRCode(@RequestBody Prezenta prezenta) throws Exception {
        try {
            String prezentaToSTr = prezenta.toString();
            return new ResponseEntity<>(QRCodeGenerator.getQRCodeImage(prezentaToSTr, 350, 350), HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }


    @CrossOrigin
    @RequestMapping(value = "/decode", method = {RequestMethod.GET})
    public ResponseEntity<?> decode() throws IOException, WriterException {
        try {
            File file = new File(QR_CODE_IMAGE_PATH + ".png");
            String decodedText = QRCodeGenerator.decodeQRCode(file);
            if (decodedText == null) {
                return new ResponseEntity<>("No QR Code found in the image", HttpStatus.NOT_FOUND);
            } else {
                Prezenta prezenta = new Prezenta();
                List<String> array = Arrays.asList(decodedText.split(","));
                prezenta.setId_disciplina(Integer.parseInt(array.get(0).split("=")[1]));
                prezenta.setId_student(Integer.parseInt(array.get(1).split("=")[1]));
                prezenta.setLaborator(Integer.parseInt(array.get(2).split("=")[1]));
//                Date date = new Date();
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
//                String strDate = formatter.format(array.get(3).split("=")[1]);
////                prezenta.setData(Date(strDate));
//                System.out.println("**************");
//                System.out.println(strDate);
                String prez = array.get(4).split("=")[1];
                prezenta.setPrezenta(prez.substring(1, prez.length() - 2));

                return new ResponseEntity<>(prezenta, HttpStatus.OK);
            }
        } catch (IOException e) {
            String err = "Could not decode QR Code, IOException :: " + e.getMessage();
            return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);

        }
    }

}
