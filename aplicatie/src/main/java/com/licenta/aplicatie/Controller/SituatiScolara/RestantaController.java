package com.licenta.aplicatie.Controller.SituatiScolara;

import com.licenta.aplicatie.Models.Programa.Disciplina;
import com.licenta.aplicatie.Models.SituatieScolara.Prezenta;
import com.licenta.aplicatie.Models.SituatieScolara.Restanta;
import com.licenta.aplicatie.Models.Users.Student;
import com.licenta.aplicatie.Service.Programa.DisciplinaService;
import com.licenta.aplicatie.Service.SituatieScolara.RestantaService;
import com.licenta.aplicatie.Service.Users.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("api/licenta/restante")
public class RestantaController {

    @Autowired
    RestantaService restantaService;
    @Autowired
    DisciplinaService disciplinaService;
    @Autowired
    StudentService studentService;

    @CrossOrigin
    @RequestMapping(value = "/disciplina={disciplina}", method = {RequestMethod.GET})
    public ResponseEntity<?> getRestForDiscipline(@PathVariable("disciplina") String disciplina) {
        try {
            Disciplina dis = disciplinaService.getDisciplinaByTitlu(disciplina);
            List<Restanta> restante = restantaService.getRestForDiscipline(dis.getId_disciplina());
            return new ResponseEntity<>(restante, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/data={data}", method = {RequestMethod.GET})
    public ResponseEntity<?> getRestForDate(@PathVariable("data") String dataStr) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date date = formatter.parse(dataStr);
            List<Restanta> restante = restantaService.getRestForDate(date);
            return new ResponseEntity<>(restante, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/student={numeStudent}", method = {RequestMethod.GET})
    public ResponseEntity<?> getRestForStudents(@PathVariable("numeStudent") String numeStudent) {
        try {
            Student student = studentService.getStudentByName(numeStudent);
            List<Restanta> restante = restantaService.getRestForStudents(student.getId_student());
            return new ResponseEntity<>(restante, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
