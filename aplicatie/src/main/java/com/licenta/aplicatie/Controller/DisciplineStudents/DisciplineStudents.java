package com.licenta.aplicatie.Controller.DisciplineStudents;

import com.licenta.aplicatie.Models.Programa.Disciplina;
import com.licenta.aplicatie.Models.Programa.ProgramaScolara;
import com.licenta.aplicatie.Models.SituatieScolara.Prezenta;
import com.licenta.aplicatie.Models.Users.Profesor;
import com.licenta.aplicatie.Models.Users.Student;
import com.licenta.aplicatie.Service.Programa.DisciplinaService;
import com.licenta.aplicatie.Service.Programa.LaboratorService;
import com.licenta.aplicatie.Service.Programa.ProgramaScolaraService;
import com.licenta.aplicatie.Service.SituatieScolara.PrezentaService;
import com.licenta.aplicatie.Service.Users.ProfesorService;
import com.licenta.aplicatie.Service.Users.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/licenta/disciplina")
public class DisciplineStudents {
    @Autowired
    DisciplinaService disciplinaService;
    @Autowired
    ProfesorService profesorService;
    @Autowired
    StudentService studentService;
    @Autowired
    ProgramaScolaraService programaScolaraService;
    @Autowired
    LaboratorService laboratorService;
    @Autowired
    PrezentaService prezentaService;


    @CrossOrigin
    @RequestMapping(value = "/titlu={titlu}/profesor={profesor}/studenti", method = {RequestMethod.GET})
    public ResponseEntity<?> getDisciplinaByTitlu(@PathVariable("titlu") String titlu, @PathVariable("profesor") String profesor) {
        try {
            Disciplina disciplina = disciplinaService.getDisciplinaByTitlu(titlu);
            Profesor prof = profesorService.getProfesorByName(profesor);
            List<String> grupe = laboratorService.getGroupsByProfesorAndDiscip(disciplina.getId_disciplina(), prof.getId_profesor());
            List<Student> allStudents = new ArrayList<>();
            for (String grupa : grupe
            ) {
                List<Student> studenti = studentService.findStudentsByGrupa(grupa);
                allStudents.addAll(studenti);
            }
            return new ResponseEntity<>(allStudents, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/titlu={titlu}/student={student}/prezente", method = {RequestMethod.GET})
    public ResponseEntity<?> getPrezente(@PathVariable("titlu") String titlu,@PathVariable("student") String student) {
        try {
            Disciplina disciplina = disciplinaService.getDisciplinaByTitlu(titlu);
            Student student_data = studentService.getStudentByName(student);
            List<Prezenta> prezentaList = prezentaService.getPrezente(disciplina.getId_disciplina(),student_data.getId_student());

            HashMap<String, Long> prezente = new HashMap<>();
            long countPrezenta = prezentaList.stream().filter(prezenta -> "prezent".equals(prezenta.getPrezenta())).count();
            long countAbsenta = prezentaList.stream().filter(prezenta -> "absent".equals(prezenta.getPrezenta())).count();
            long countRecuperat = prezentaList.stream().filter(prezenta -> "recuperat".equals(prezenta.getPrezenta())).count();

            prezente.put("prezent",countPrezenta);
            prezente.put("absent",countAbsenta);
            prezente.put("recuperat",countRecuperat);

            return new ResponseEntity<>(prezente, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/disciplina={disciplina}/grupa={grupa}/laborator={laborator}/studenti", method = {RequestMethod.POST})
    public ResponseEntity<?> sendEmailToAllStudents(@PathVariable("disciplina") String disciplina, @PathVariable("grupa") String grupa,@PathVariable("laborator") String laborator) {
        try {
            Disciplina dis = disciplinaService.getDisciplinaByTitlu(disciplina);
            List<Student> studenti = studentService.findStudentsByGrupa(grupa);
            for (Student student:studenti
                 ) {
                System.out.println(student.getNume()+" - "+dis.getId_disciplina()+" - "+laborator );
            }

            return new ResponseEntity<>("All email were send", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
