package com.licenta.aplicatie.Controller;

import com.licenta.aplicatie.Models.Profesor;
import com.licenta.aplicatie.Models.Studenti.Student;
import com.licenta.aplicatie.Service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/licenta/teachers/")
public class ProfesorController {
    @Autowired
    ProfesorService profesorService;

    @CrossOrigin
    @RequestMapping(value = "/fullname={fullname}", method = {RequestMethod.GET})
    public ResponseEntity<?> getProfesorByFullName(@PathVariable("fullname") String name) {
        try {
            Profesor profesor = profesorService.getProfesorByName(name);
            return new ResponseEntity<>(profesor, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/email={email}", method = {RequestMethod.GET})
    public ResponseEntity<?> getProfesorByEmail(@PathVariable("email") String email) {
        try {
            Profesor profesor = profesorService.getProfesorByEmail(email);
            return new ResponseEntity<>(profesor, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @CrossOrigin
    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> getProfesorById(@PathVariable("id") int id) {
        try {
            Profesor profesor = profesorService.getProfesorById(id);
            return new ResponseEntity<>(profesor, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/firstname={firstname}", method = {RequestMethod.GET})
    public ResponseEntity<?> getProfesorByFirstname(@PathVariable("firstname") String firstname) {
        try {
            List<Profesor> profesors = profesorService.getProfesorByFirstName(firstname);
            return new ResponseEntity<>(profesors, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
