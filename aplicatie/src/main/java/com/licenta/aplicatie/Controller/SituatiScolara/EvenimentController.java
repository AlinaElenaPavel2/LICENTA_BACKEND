package com.licenta.aplicatie.Controller.SituatiScolara;

import com.licenta.aplicatie.Models.Programa.Disciplina;
import com.licenta.aplicatie.Models.SituatieScolara.Eveniment;
import com.licenta.aplicatie.Models.SituatieScolara.Prezenta;
import com.licenta.aplicatie.Models.Users.Student;
import com.licenta.aplicatie.Repository.SituatieScolara.EvenimentRepository;
import com.licenta.aplicatie.Service.Programa.DisciplinaService;
import com.licenta.aplicatie.Service.Programa.ProgramaScolaraService;
import com.licenta.aplicatie.Service.SituatieScolara.EvenimentService;
import com.licenta.aplicatie.Service.Users.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/licenta/evenimente")
public class EvenimentController {
    @Autowired
    EvenimentService evenimentService;
    @Autowired
    DisciplinaService disciplinaService;
    @Autowired
    StudentService studentService;
    @Autowired
    ProgramaScolaraService programaScolaraService;

    @CrossOrigin

    @RequestMapping(value = "/disciplina={disciplina}", method = {RequestMethod.GET})
    public ResponseEntity<?> getEvenimentByDisciplina(@PathVariable("disciplina") String disciplina) {
        try {
            Disciplina dis = disciplinaService.getDisciplinaByTitlu(disciplina);
            System.out.println(dis.getId_disciplina());
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
            System.out.println(eveniment.toString());
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

    @CrossOrigin
    @RequestMapping(value = "/student={nume}", method = {RequestMethod.GET})
    public ResponseEntity<?> getEvenimente(@PathVariable("nume") String nume) {
        try {
            Student student = studentService.getStudentByName(nume);
            List<Disciplina> discipline = programaScolaraService.getDisciplinesBySpecializareAndAn(student.getProgram_studiu(), student.getSpecializare(), student.getAn());
            List<Eveniment> evenimente = new ArrayList<>();
            for (int i = 4; i < 8; i++) {
                System.out.println(discipline.get(i).getId_disciplina());
//                Eveniment ev = evenimentService.getEveniment(discipline.get(i).getId_disciplina());
                Eveniment ev = evenimentService.getEveniment2(discipline.get(i).getId_disciplina());
                if(ev!=null) {
                    evenimente.add(ev);
                }
            }
            return new ResponseEntity<>(evenimente, HttpStatus.CREATED);

        } catch (Exception ex) {
            return new ResponseEntity<>("Eveniments not found!", HttpStatus.NO_CONTENT);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/disciplina={disciplina}/titlu={titlu}/startDate={startDate}", method = {RequestMethod.DELETE})
    public ResponseEntity<?> deleteEvenimentByDisciplina(@PathVariable("disciplina") String disciplina, @PathVariable("titlu") String titlu, @PathVariable("startDate") String startDate) {
        try {
            Disciplina dis = disciplinaService.getDisciplinaByTitlu(disciplina);
            Eveniment eveniment = new Eveniment();
            eveniment.setId_disciplina(dis.getId_disciplina());
            eveniment.setTitlu(titlu);
            eveniment.setStart_date(startDate);
            System.out.println(eveniment);
            evenimentService.deleteEveniment(eveniment);
            return new ResponseEntity<>("Eveniment have been deleted!", HttpStatus.CREATED);

        } catch (Exception ex) {
            return new ResponseEntity<>("Eveniment have been updated!", HttpStatus.NO_CONTENT);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT})
    public ResponseEntity<?> updateEveniment(@PathVariable("id") int id, @RequestBody Eveniment newWveniment) {
        try {
            evenimentService.updateEveniment(id, newWveniment);
            return new ResponseEntity<>("Eveniment have been updated!", HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>("Eveniment have been updated!", HttpStatus.NO_CONTENT);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/student={nume}/all", method = {RequestMethod.GET})
    public ResponseEntity<?> getEvenimenteD(@PathVariable("nume") String nume) {
        try {
            Student student = studentService.getStudentByName(nume);
            List<Disciplina> discipline = programaScolaraService.getDiscipline(student.getProgram_studiu(), student.getSpecializare(), student.getAn(),2);
            List<Eveniment> evenimente = new ArrayList<>();
            for (int i = 0; i < discipline.size(); i++) {
                List<Eveniment> ev = evenimentService.getEvenimente2(discipline.get(i).getId_disciplina());
                if(ev!=null) {
                    evenimente.addAll(ev);
                }
            }
            System.out.println(evenimente.size());
            return new ResponseEntity<>(evenimente, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Eveniments not found! "+ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
