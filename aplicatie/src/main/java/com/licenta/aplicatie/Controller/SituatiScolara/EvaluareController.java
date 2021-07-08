package com.licenta.aplicatie.Controller.SituatiScolara;

import com.licenta.aplicatie.Models.Programa.Disciplina;
import com.licenta.aplicatie.Models.SituatieScolara.Evaluare;
import com.licenta.aplicatie.Repository.SituatieScolara.EvaluareRepository;
import com.licenta.aplicatie.Service.Programa.DisciplinaService;
import com.licenta.aplicatie.Service.SituatieScolara.EvaluareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;

@RestController
@RequestMapping("api/licenta/evaluare")
public class EvaluareController {

    @Autowired
    EvaluareService evaluareService;
    @Autowired
    DisciplinaService disciplinaService;

    @CrossOrigin
    @RequestMapping(value = "/disciplina={nume}", method = {RequestMethod.GET})
    public ResponseEntity<?> getDisciplinaByTitlu(@PathVariable("nume") String nume) {
        try {
            Disciplina disciplina = disciplinaService.getDisciplinaByTitlu(nume);
            Evaluare evaluare=evaluareService.getProcents(disciplina.getId_disciplina());
            return new ResponseEntity<>(evaluare, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
