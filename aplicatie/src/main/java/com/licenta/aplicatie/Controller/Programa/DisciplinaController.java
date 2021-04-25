package com.licenta.aplicatie.Controller.Programa;

import com.licenta.aplicatie.Models.Programa.Disciplina;
import com.licenta.aplicatie.Models.Users.Profesor;
import com.licenta.aplicatie.Service.Programa.DisciplinaService;
import com.licenta.aplicatie.Service.Users.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            List<Integer> id_disciplina = disciplinaService.getIdDiscipline(titlu);
            return new ResponseEntity<>(id_disciplina, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/titular/nume={nume}", method = {RequestMethod.GET})
    public ResponseEntity<?> getDisciplinaByTitularNume(@PathVariable("nume") String nume) {
        try {
            List<Optional<Profesor>> profesori=profesorService.getProfesorByName(nume);
            List<Disciplina> discipline=new ArrayList<>();
            for (Optional<Profesor> profesor:profesori
                 ) {
                if(profesor.isPresent())
                {
                   discipline.addAll(disciplinaService.getDisciplinaByIdTitular(profesor.get().getId_profesor()));
                }
            }
            return new ResponseEntity<>(discipline, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin
    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public ResponseEntity<?> getAll()  {
        try {
           List<Disciplina> discipline=disciplinaService.getAllDisciplines();
            return new ResponseEntity<>(discipline, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin
    @RequestMapping(value = "/abreviere={abreviere}", method = {RequestMethod.GET})
    public ResponseEntity<?> getDisciplinaByAbreviere(@PathVariable("abreviere") String abreviere)  {
        try {
            List<Disciplina> disciplina=disciplinaService.getDisciplina(abreviere);
            return new ResponseEntity<>(disciplina, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
