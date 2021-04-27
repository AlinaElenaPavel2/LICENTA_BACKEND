package com.licenta.aplicatie.Controller.SituatiScolara;

import com.licenta.aplicatie.Models.Programa.Disciplina;
import com.licenta.aplicatie.Models.SituatieScolara.Eveniment;
import com.licenta.aplicatie.Models.SituatieScolara.Prezenta;
import com.licenta.aplicatie.Repository.SituatieScolara.EvenimentRepository;
import com.licenta.aplicatie.Service.Programa.DisciplinaService;
import com.licenta.aplicatie.Service.SituatieScolara.EvenimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/licenta/evenimente")
public class EvenimentController {
    @Autowired
    EvenimentService evenimentService;
    @Autowired
    DisciplinaService disciplinaService;

    @CrossOrigin
    @RequestMapping(value = "/disciplina={disciplina}", method = {RequestMethod.GET})
    public ResponseEntity<?> getEvenimentByDisciplina(@PathVariable("disciplina") String disciplina) {
        try {
            Disciplina dis = disciplinaService.getDisciplinaByTitlu(disciplina);
            List<Eveniment> evenimente = evenimentService.getEvenimente(dis.getId_disciplina());
            return new ResponseEntity<>(evenimente, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/disciplina={disciplina}", method = {RequestMethod.POST})
    public ResponseEntity<?> addEvenimentByDisciplina(@RequestBody Eveniment eveniment, @PathVariable("disciplina") String disciplina) {
        try {
            Disciplina dis = disciplinaService.getDisciplinaByTitlu(disciplina);
            eveniment.setId_disciplina(dis.getId_disciplina());
            evenimentService.saveEveniment(eveniment);
            return new ResponseEntity<>("Eveniment have been created!", HttpStatus.CREATED);

        } catch (Exception ex) {
            return new ResponseEntity<>("Eveniment have been updated!", HttpStatus.NO_CONTENT);
        }
    }
    @CrossOrigin
    @RequestMapping(value = "/disciplina={disciplina}", method = {RequestMethod.DELETE})
    public ResponseEntity<?> deleteEvenimentByDisciplina(@RequestBody Eveniment eveniment, @PathVariable("disciplina") String disciplina) {
        try {
            Disciplina dis = disciplinaService.getDisciplinaByTitlu(disciplina);
            eveniment.setId_disciplina(dis.getId_disciplina());
            System.out.println(eveniment);
            evenimentService.deleteEveniment(eveniment);
            return new ResponseEntity<>("Eveniment have been created!", HttpStatus.CREATED);

        } catch (Exception ex) {
            return new ResponseEntity<>("Eveniment have been updated!", HttpStatus.NO_CONTENT);
        }
    }
}
