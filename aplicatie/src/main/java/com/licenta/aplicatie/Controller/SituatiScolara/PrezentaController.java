package com.licenta.aplicatie.Controller.SituatiScolara;

import com.licenta.aplicatie.Models.Programa.Disciplina;
import com.licenta.aplicatie.Models.SituatieScolara.Prezenta;
import com.licenta.aplicatie.Models.Users.Student;
import com.licenta.aplicatie.Service.Programa.DisciplinaService;
import com.licenta.aplicatie.Service.SituatieScolara.PrezentaService;
import com.licenta.aplicatie.Service.Users.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/licenta/prezenta")
public class PrezentaController {

    @Autowired
    PrezentaService prezentaService;
    @Autowired
    DisciplinaService disciplinaService;
    @Autowired
    StudentService studentService;

    @CrossOrigin
    @RequestMapping(value = "/disciplina={disciplina}", method = {RequestMethod.GET})
    public ResponseEntity<?> getPrezentaByDisciplina(@PathVariable("disciplina") String disciplina) {
        try {
        Disciplina dis=disciplinaService.getDisciplinaByTitlu(disciplina);
        List<Prezenta> prezentaList=prezentaService.getPrezente(dis.getId_disciplina());
            return new ResponseEntity<>(prezentaList,HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/student={student}", method = {RequestMethod.GET})
    public ResponseEntity<?> getPrezenteByStudent(@PathVariable("student") String student) {
        try {
            Student stud=studentService.getStudentByName(student);
            List<Prezenta> prezentaList=prezentaService.getPrezenteByStudent(stud.getId_student());
            return new ResponseEntity<>(prezentaList,HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/disciplina={disciplina}/student={student}", method = {RequestMethod.GET})
    public ResponseEntity<?> getPrezente(@PathVariable("disciplina") String disciplina,@PathVariable("student") String student) {
        try {
            Disciplina dis=disciplinaService.getDisciplinaByTitlu(disciplina);
            Student stud=studentService.getStudentByName(student);
            List<Prezenta> prezentaList=prezentaService.getPrezente(dis.getId_disciplina(),stud.getId_student());
            return new ResponseEntity<>(prezentaList,HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
