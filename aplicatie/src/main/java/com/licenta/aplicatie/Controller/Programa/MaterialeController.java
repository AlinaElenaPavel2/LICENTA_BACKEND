package com.licenta.aplicatie.Controller.Programa;

import com.licenta.aplicatie.Models.Programa.Disciplina;
import com.licenta.aplicatie.Models.Users.Profesor;
import com.licenta.aplicatie.Service.Programa.DisciplinaService;
import com.licenta.aplicatie.Service.Programa.MaterialeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/licenta/materiale")
public class MaterialeController {
    @Autowired
    DisciplinaService disciplinaService;
    @Autowired
    MaterialeService materialeService;

    @CrossOrigin
    @RequestMapping(value = "/disciplina={disciplina}/tip={tip}", method = {RequestMethod.GET})
    public ResponseEntity<?> getProfByGrupa(@PathVariable("tip") String tip, @PathVariable("disciplina") String disciplina) {
        try {
            Disciplina discip = disciplinaService.getDisciplinaByTitlu(disciplina);
            List<String> paths =materialeService.getAllPaths(discip.getId_disciplina(),tip);
            return new ResponseEntity<>(paths, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }



}
