package com.licenta.aplicatie.Controller.Discipline;

import com.licenta.aplicatie.Models.Discipline.Disciplina;
import com.licenta.aplicatie.Models.Profesor;
import com.licenta.aplicatie.Service.Discipline.DisciplinaService;
import com.licenta.aplicatie.Service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/licenta/discipline")
public class DisciplinaController {
    @Autowired
    DisciplinaService disciplinaService;
    @Autowired
    ProfesorService profesorService;

    @CrossOrigin
    @RequestMapping(value = "/titlu={titlu}", method = {RequestMethod.GET})
    public ResponseEntity<?> getDisciplinaByTitlu(@PathVariable("titlu") String titlu) {
        try {
            Disciplina disciplina = disciplinaService.getDisciplinaByTitlu(titlu);
            return new ResponseEntity<>(disciplina, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/titlu={titlu}/credite", method = {RequestMethod.GET})
    public ResponseEntity<?> getCredite(@PathVariable("titlu") String titlu) {
        try {
            int credite = disciplinaService.getCredite(titlu);
            return new ResponseEntity<>(credite, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/titlu={titlu}/id", method = {RequestMethod.GET})
    public ResponseEntity<?> getIdByTitlu(@PathVariable("titlu") String titlu) {
        try {
            int id_disciplina = disciplinaService.getIdDiscipline(titlu);
            return new ResponseEntity<>(id_disciplina, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/titular/nume={nume}", method = {RequestMethod.GET})
    public ResponseEntity<?> getDisciplinaByTitularNume(@PathVariable("nume") String nume) {
        try {
            Profesor profesor=profesorService.getProfesorByName(nume);
            System.out.println(profesor.getId());
            List<Disciplina> discipline= disciplinaService.getDisciplinaByIdTitular(profesor.getId());
            return new ResponseEntity<>(discipline, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
