package com.licenta.aplicatie.Controller.SituatiScolara;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.licenta.aplicatie.Models.Programa.Disciplina;
import com.licenta.aplicatie.Models.SituatieScolara.Evaluare;
import com.licenta.aplicatie.Models.SituatieScolara.SituatieScolara;
import com.licenta.aplicatie.Models.Users.Student;
import com.licenta.aplicatie.Service.Programa.DisciplinaService;
import com.licenta.aplicatie.Service.SituatieScolara.SituatieScolaraService;
import com.licenta.aplicatie.Service.Users.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/licenta/situatieScolara")
public class SituatieScolaraController {
    @Autowired
    SituatieScolaraService situatieScolaraService;
    @Autowired
    DisciplinaService disciplinaService;
    @Autowired
    StudentService studentService;


    @CrossOrigin
    @RequestMapping(value = "/disciplina={nume}/student={numeStudent}/medie", method = {RequestMethod.GET})
    public ResponseEntity<?> getMedie(@PathVariable("nume") String nume, @PathVariable("numeStudent") String numeStudent) {
        try {
            Disciplina disciplina = disciplinaService.getDisciplinaByTitlu(nume);
            Student student = studentService.getStudentByName(numeStudent);
            int medie = situatieScolaraService.getMedieByDisciplina(disciplina.getId_disciplina(), student.getId_student());
            return new ResponseEntity<>(medie, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/student={nume}/medii", method = {RequestMethod.GET})
    public ResponseEntity<?> getMedii(@PathVariable("nume") String nume) {
        try {
            Student student = studentService.getStudentByName(nume);
            List<Integer> medii = situatieScolaraService.getMedii(student.getId_student());
            return new ResponseEntity<>(medii, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

//    @CrossOrigin
//    @RequestMapping(value = "/student={nume}/medii/discipline", method = {RequestMethod.GET})
//    public ResponseEntity<?> getMediiDisicpline(@PathVariable("nume") String nume) {
//        try {
//            Student student = studentService.getStudentByName(nume);
//            Map<String,Integer> results=new HashMap<>();
//            List<String> discipline=new ArrayList<>();
//            List<Integer> medii=new ArrayList<>();
//            List<SituatieScolara> situatieScolara=situatieScolaraService.getMediiForDiscipline(student.getId_student());
//            for  (SituatieScolara s:situatieScolara
//                 ) {
//                System.out.println(s.getMedie());
//                System.out.println(s.getId_disciplina());
//                results.put(disciplinaService.getDisciplina(s.getId_disciplina()).getNume(),s.getMedie());
//                discipline.add(disciplinaService.getDisciplina(s.getId_disciplina()).getNume());
//                medii.add(s.getMedie());
//            }
//            Map map = new HashMap();
//            map.put("discipline", discipline);
//            map.put("medii", medii);
//
//            return new ResponseEntity<>(map, HttpStatus.OK);
//        } catch (Exception ex) {
//            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }

    @CrossOrigin
    @RequestMapping(value = "/student={nume}/medii/discipline", method = {RequestMethod.GET})
    public ResponseEntity<?> getMediiDisicpline(@PathVariable("nume") String nume) {
        try {
            Student student = studentService.getStudentByName(nume);
            List<Integer> discipline = situatieScolaraService.getDiscipline(student.getId_student());
            List<Integer> medii = situatieScolaraService.getMedii(student.getId_student());
            List<String> disciplineNum = new ArrayList<>();
            for (Integer disciplina : discipline
            ) {
                disciplineNum.add(disciplinaService.getDisciplina(disciplina).getNume());
            }
            Map map = new HashMap();
            map.put("discipline", disciplineNum);
            map.put("medii", medii);

            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
