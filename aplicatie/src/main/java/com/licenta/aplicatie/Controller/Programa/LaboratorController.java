package com.licenta.aplicatie.Controller.Programa;

import com.licenta.aplicatie.Models.Programa.Disciplina;
import com.licenta.aplicatie.Models.Programa.Laborator;
import com.licenta.aplicatie.Models.Users.Profesor;
import com.licenta.aplicatie.Service.Programa.DisciplinaService;
import com.licenta.aplicatie.Service.Programa.LaboratorService;
import com.licenta.aplicatie.Service.Users.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/licenta/laborator")
public class LaboratorController {
    @Autowired
    LaboratorService laboratorService;
    @Autowired
    ProfesorService profesorService;
    @Autowired
    DisciplinaService disciplinaService;

    @CrossOrigin
    @RequestMapping(value = "/disciplina={disciplina}/grupa={grupa}", method = {RequestMethod.GET})
    public ResponseEntity<?> getProfByGrupa(@PathVariable("grupa") String grupa, @PathVariable("disciplina") String disciplina) {
        try {
            Disciplina discip = disciplinaService.getDisciplinaByTitlu(disciplina);
            int id_prof = laboratorService.getLabProfesor(discip.getId_disciplina(), grupa);
            Profesor prof = profesorService.getProfesorById(id_prof);
            return new ResponseEntity<>(prof, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin
    @RequestMapping(value = "/disciplina={disciplina}/profesori", method = {RequestMethod.GET})
    public ResponseEntity<?> getProfesori( @PathVariable("disciplina") String disciplina) {
        try {
            Disciplina discip = disciplinaService.getDisciplinaByTitlu(disciplina);
            List<Integer> prof_Ids = laboratorService.getLabProfesori(discip.getId_disciplina());
            List<Profesor> profesori=new ArrayList<>();
            for (Integer id:prof_Ids
                 ) {
                Profesor prof = profesorService.getProfesorById(id);
                profesori.add(prof);
            }
            List<Profesor> profDistinct = profesori.stream().distinct().collect(Collectors.toList());
            return new ResponseEntity<>(profDistinct, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/disciplina={disciplina}/profesori/grupe", method = {RequestMethod.GET})
    public ResponseEntity<?> getProfesoriAndGrops( @PathVariable("disciplina") String disciplina) {
        try {
            Disciplina discip = disciplinaService.getDisciplinaByTitlu(disciplina);
            System.out.println(discip.getId_disciplina());
            List<Laborator> laboratoare = laboratorService.getLabProfesoriAngGroups(discip.getId_disciplina());
            List<Profesor> profesori=new ArrayList<>();
            List<String> grupe=new ArrayList<>();
            for (Laborator lab:laboratoare
            ) {
                Profesor prof = profesorService.getProfesorById(lab.getId_profesor());
                profesori.add(prof);
                grupe.add(lab.getGrupa());
            }
            Map map = new HashMap();
            map.put("profesori", profesori);
            map.put("grupe", grupe);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/disciplina={disciplina}/profesor={profesor}", method = {RequestMethod.GET})
    public ResponseEntity<?> getProfesori( @PathVariable("disciplina") String disciplina,@PathVariable("profesor") String profesor) {
        try {
            Disciplina discip = disciplinaService.getDisciplinaByTitlu(disciplina);
            Profesor prof=profesorService.getProfesorByName(profesor);
            List<String> grupe=laboratorService.getGroupsByProfesorAndDiscip(discip.getId_disciplina(),prof.getId_profesor());
            return new ResponseEntity<>(grupe, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
