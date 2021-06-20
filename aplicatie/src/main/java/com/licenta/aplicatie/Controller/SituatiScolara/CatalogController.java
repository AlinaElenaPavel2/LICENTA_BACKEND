package com.licenta.aplicatie.Controller.SituatiScolara;

import com.licenta.aplicatie.Models.Programa.Disciplina;
import com.licenta.aplicatie.Models.SituatieScolara.Catalog;
import com.licenta.aplicatie.Models.Users.Student;
import com.licenta.aplicatie.Service.Programa.DisciplinaService;
import com.licenta.aplicatie.Service.SituatieScolara.CatalogService;
import com.licenta.aplicatie.Service.Users.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/licenta/catalog")
public class CatalogController {
    @Autowired
    CatalogService catalogService;
    @Autowired
    DisciplinaService disciplinaService;
    @Autowired
    StudentService studentService;

    @CrossOrigin
    @RequestMapping(value = "/disciplina={nume}/student={numeStudent}", method = {RequestMethod.GET})
    public ResponseEntity<?> getMark(@PathVariable("nume") String nume,@PathVariable("numeStudent") String numeStudent) {
        try {
            Disciplina disciplina = disciplinaService.getDisciplinaByTitlu(nume);
            Student student=studentService.getStudentByName(numeStudent);
            float nota=catalogService.getFinalMark(disciplina.getId_disciplina(),student.getId_student());
            return new ResponseEntity<>(nota, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/disciplina={nume}/student={numeStudent}/note", method = {RequestMethod.GET})
    public ResponseEntity<?> getMarks(@PathVariable("nume") String nume,@PathVariable("numeStudent") String numeStudent) {
        try {
            Disciplina disciplina = disciplinaService.getDisciplinaByTitlu(nume);
            Student student=studentService.getStudentByName(numeStudent);
            Catalog note=catalogService.getMarks(disciplina.getId_disciplina(),student.getId_student());
            return new ResponseEntity<>(note, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/student={nume}/an={an}/situatie", method = {RequestMethod.GET})
    public ResponseEntity<?> getMarks(@PathVariable("nume") String nume,@PathVariable("an") int an) {
        try {
            Student student=studentService.getStudentByName(nume);
            HashMap<String,Float> situatie=catalogService.getFinalSituation(student.getId_student(),student.getProgram_studiu(),student.getSpecializare(),an);

            return new ResponseEntity<>(situatie, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/student={nume}/disciplina={disciplina}", method = {RequestMethod.PUT})
    public ResponseEntity<?> updateMarks(@PathVariable("nume") String nume,@PathVariable("disciplina") String disciplina,@RequestBody Catalog note) {
        try {
            Student student=studentService.getStudentByName(nume);
            Disciplina discip = disciplinaService.getDisciplinaByTitlu(disciplina);
            System.out.println(student.getId_student());
            System.out.println(discip.getId_disciplina());
            Catalog updated = catalogService.updateMark(student.getId_student(),discip.getId_disciplina(),note);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "disciplina={disciplina}/tip={tip}", method = {RequestMethod.GET})
    public ResponseEntity<?> getAllMarks(@PathVariable("disciplina") String disciplina,@PathVariable("tip") String tip) {
        try {
            Disciplina discip = disciplinaService.getDisciplinaByTitlu(disciplina);
            Map<Integer,Integer> selected=catalogService.getNoteDisciplina(discip.getId_disciplina(),tip);
            return new ResponseEntity<>(selected, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "disciplina={disciplina}/medieFinala", method = {RequestMethod.GET})
    public ResponseEntity<?> getAllFinalMarks(@PathVariable("disciplina") String disciplina) {
        try {
            Disciplina discip = disciplinaService.getDisciplinaByTitlu(disciplina);
            Map<Integer,Integer> selected=catalogService.getAllFinalMark(discip.getId_disciplina());
            return new ResponseEntity<>(selected, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
