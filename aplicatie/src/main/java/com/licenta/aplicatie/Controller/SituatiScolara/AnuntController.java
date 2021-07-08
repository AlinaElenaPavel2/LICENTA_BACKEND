package com.licenta.aplicatie.Controller.SituatiScolara;

import com.licenta.aplicatie.Models.Programa.Disciplina;
import com.licenta.aplicatie.Models.SituatieScolara.Anunt;
import com.licenta.aplicatie.Models.Users.Student;
import com.licenta.aplicatie.Service.Programa.DisciplinaService;
import com.licenta.aplicatie.Service.SituatieScolara.AnuntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/licenta/anunturi")
public class AnuntController {
    @Autowired
    AnuntService anuntService;
    @Autowired
    DisciplinaService disciplinaService;

    @CrossOrigin
    @RequestMapping(value = "/disciplina={materie}/grupa={grupa}", method = {RequestMethod.GET})
    public ResponseEntity<?> getAnunt(@PathVariable("materie") String materie, @PathVariable("grupa") String grupa) {
        try {
            Disciplina disciplina = disciplinaService.getDisciplinaByTitlu(materie);
            List<Anunt> anunturi=anuntService.getAnunturi(disciplina.getId_disciplina(),grupa);
            return new ResponseEntity<>(anunturi, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/disciplina={materie}/grupa={grupa}", method = {RequestMethod.POST})
    public ResponseEntity<?> postAnunt(@PathVariable("materie") String materie, @PathVariable("grupa") String grupa,@RequestBody Anunt anunt) {
        try {
            Disciplina disciplina = disciplinaService.getDisciplinaByTitlu(materie);
            anunt.setId_disciplina(disciplina.getId_disciplina());
            anunt.setGrupa(grupa);
            anuntService.addAnunt(anunt);
            return new ResponseEntity<>(anunt, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
