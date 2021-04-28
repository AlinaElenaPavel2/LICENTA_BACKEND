package com.licenta.aplicatie.Controller.QRGenerator;

import com.google.zxing.WriterException;
import com.licenta.aplicatie.Models.SituatieScolara.Prezenta;
import com.licenta.aplicatie.POJO.QRCodeGenerator;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("api/licenta/qrCode")
public class QRCodeController {
    private static final String QR_CODE_IMAGE_PATH = "aplicatie/src/main/resources/QRCodes/QRCode.png";

    @CrossOrigin
    @RequestMapping(value = "/generate", method = {RequestMethod.POST})
    public void download(@RequestBody Prezenta prezenta) throws Exception {
        String prezentaToSTr = prezenta.toString();
        QRCodeGenerator.generateQRCodeImage(prezentaToSTr, 350, 350, QR_CODE_IMAGE_PATH);

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
            File file = new File(QR_CODE_IMAGE_PATH);
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
