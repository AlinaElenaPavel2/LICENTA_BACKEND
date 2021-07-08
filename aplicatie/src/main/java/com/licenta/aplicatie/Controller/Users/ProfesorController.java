package com.licenta.aplicatie.Controller.Users;

import com.licenta.aplicatie.Models.Users.Profesor;
import com.licenta.aplicatie.Service.Users.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @CrossOrigin
    @RequestMapping(value = "/teacher/email={email}", method = {RequestMethod.GET})
    public ResponseEntity<?> getIdByEmail(@PathVariable("email") String email) {
        try {
            Integer profesorsid_prof = profesorService.getProfesorIdByEmail(email);
            return new ResponseEntity<>(profesorsid_prof, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/teacher/nume={nume}", method = {RequestMethod.GET})
    public ResponseEntity<?> getProfIdByEmail(@PathVariable("nume") String nume) {
        try {
            Integer profesorsid_prof = profesorService.getProfesorIdByName(nume);
            return new ResponseEntity<>(profesorsid_prof, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
