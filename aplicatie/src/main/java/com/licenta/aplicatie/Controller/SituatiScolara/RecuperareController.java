package com.licenta.aplicatie.Controller.SituatiScolara;

import com.licenta.aplicatie.Models.Programa.Disciplina;
import com.licenta.aplicatie.Models.SituatieScolara.Prezenta;
import com.licenta.aplicatie.Models.SituatieScolara.Recuperare;
import com.licenta.aplicatie.Models.Users.Profesor;
import com.licenta.aplicatie.Models.Users.Student;
import com.licenta.aplicatie.Service.Programa.DisciplinaService;
import com.licenta.aplicatie.Service.Programa.LaboratorService;
import com.licenta.aplicatie.Service.SituatieScolara.RecuperareService;
import com.licenta.aplicatie.Service.Users.ProfesorService;
import com.licenta.aplicatie.Service.Users.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/licenta/recuperare")
public class RecuperareController {
    @Autowired
    RecuperareService recuperareService;
    @Autowired
    DisciplinaService disciplinaService;
    @Autowired
    StudentService studentService;
    @Autowired
    ProfesorService profesorService;
    @Autowired
    LaboratorService laboratorService;

    @CrossOrigin
    @RequestMapping(value = "/disciplina={disciplina}/profesor={profesor}", method = {RequestMethod.GET})
    public ResponseEntity<?> getCereri(@PathVariable("disciplina") String disciplina,@PathVariable("profesor") String profesor) {
        try {
            Disciplina dis = disciplinaService.getDisciplinaByTitlu(disciplina);
            Profesor prof=profesorService.getProfesorByName(profesor);
            List<String> grupe=laboratorService.getGroupsByProfesorAndDiscip(dis.getId_disciplina(),prof.getId_profesor());
            List<Recuperare> recuperariAll=new ArrayList<>();
            for (String grupa: grupe
                 ) {
                List<Recuperare> recuperari=recuperareService.getCereriByGrupa(dis.getId_disciplina(),grupa);
                recuperariAll.addAll(recuperari);
            }

            return new ResponseEntity<>(recuperariAll, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}/response={response}", method = {RequestMethod.POST})
    public ResponseEntity<?> setResponse(@PathVariable("id") Integer id,@PathVariable("response") String response) {
        try {
            Recuperare recuperare=recuperareService.setProfResponse(id,response);
            return new ResponseEntity<>(recuperare, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/disciplina={disciplina}/student={student}", method = {RequestMethod.POST})
    public ResponseEntity<?> adaugareCerereRecuperare(@PathVariable("disciplina") String disciplina,@PathVariable("student") String student,@RequestBody Recuperare recuperare) {
        try {
           Disciplina dis=disciplinaService.getDisciplinaByTitlu(disciplina);
            Student stud=studentService.getStudentByName(student);
            recuperare.setId_disciplina(dis.getId_disciplina());
            recuperare.setId_student(stud.getId_student());
            recuperareService.adaugareCerereRecuperare(recuperare);
            return new ResponseEntity<>(recuperare, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
