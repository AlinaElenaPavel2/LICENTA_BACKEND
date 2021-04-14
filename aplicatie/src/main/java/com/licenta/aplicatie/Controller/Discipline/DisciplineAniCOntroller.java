package com.licenta.aplicatie.Controller.Discipline;

import com.licenta.aplicatie.Models.Discipline.Disciplina;
import com.licenta.aplicatie.Service.Discipline.DisciplineAniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/licenta/discipline")
public class DisciplineAniCOntroller {
    @Autowired
    DisciplineAniService disciplineAniService;

    @CrossOrigin
    @RequestMapping(value = "/an={an}", method = {RequestMethod.GET})
    public ResponseEntity<?> getDisciplinaByYear(@PathVariable("an") int an) {
        try {
            List<Integer> discipline = disciplineAniService.getDiscipleByYear(an);
            return new ResponseEntity<>(discipline, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/an={an}/semestru={semestru}", method = {RequestMethod.GET})
    public ResponseEntity<?> getDisciplinaByTitlu(@PathVariable("an") int an, @PathVariable("semestru") int semestru) {
        try {
            List<Integer> discipline = disciplineAniService.getDiscipleByYear(an, semestru);
            return new ResponseEntity<>(discipline, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/an={an}/semestru={semestru}/specializare={specializare}", method = {RequestMethod.GET})
    public ResponseEntity<?> getDisciplinaByTitlu(@PathVariable("an") int an, @PathVariable("semestru") int semestru, @PathVariable("specializare") String specializare) {
        try {
            List<Integer> discipline = disciplineAniService.getDiscipleAll(an, semestru, specializare);
            return new ResponseEntity<>(discipline, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
