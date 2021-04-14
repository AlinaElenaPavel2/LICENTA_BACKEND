package com.licenta.aplicatie.Controller.Student;

import com.licenta.aplicatie.Models.Studenti.DetaliiStudent;
import com.licenta.aplicatie.Service.Student.DetaliiStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/licenta/students")
public class DetaliiStudentiController {
    @Autowired
    DetaliiStudentService detaliiStudentService;

    @CrossOrigin
    @RequestMapping(value = "/an={an}", method = {RequestMethod.GET})
    public ResponseEntity<?> getStudentsByYear(@PathVariable("an") int an) {
        try {
            List<DetaliiStudent> detaliiStudents = detaliiStudentService.findStudentsByAn(an);
            return new ResponseEntity<>(detaliiStudents, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @CrossOrigin
    @RequestMapping(value = "/grupa={grupa}", method = {RequestMethod.GET})
    public ResponseEntity<?> getStudentsByGrupa(@PathVariable("grupa") String grupa) {
        try {
            List<DetaliiStudent> detaliiStudents = detaliiStudentService.findStudentsByGrupa(grupa);
            return new ResponseEntity<>(detaliiStudents, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @CrossOrigin
    @RequestMapping(value = "/specializare={specializare}", method = {RequestMethod.GET})
    public ResponseEntity<?> getStudentsBySpecializare(@PathVariable("specializare") String specializare) {
        try {
            List<DetaliiStudent> detaliiStudents = detaliiStudentService.findStudentsBySpecializare(specializare);
            return new ResponseEntity<>(detaliiStudents, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @CrossOrigin
    @RequestMapping(value = "/specializare={specializare}/grupa={grupa}", method = {RequestMethod.GET})
    public ResponseEntity<?> getStudentsBySpecializareAndGrupa(@PathVariable("specializare") String specializare,@PathVariable("grupa") String grupa) {
        try {
            List<DetaliiStudent> detaliiStudents = detaliiStudentService.findStudentsBySpecializareAndGrupa(specializare,grupa);
            return new ResponseEntity<>(detaliiStudents, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @CrossOrigin
    @RequestMapping(value = "/an={an}/specializare={specializare}", method = {RequestMethod.GET})
    public ResponseEntity<?> getStudentsBySpecializareAndAn(@PathVariable("specializare") String specializare,@PathVariable("an") int an) {
        try {
            List<DetaliiStudent> detaliiStudents = detaliiStudentService.findStudentsBySpecializareAndAn(specializare,an);
            return new ResponseEntity<>(detaliiStudents, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @CrossOrigin
    @RequestMapping(value = "/an={an}/grupa={grupa}", method = {RequestMethod.GET})
    public ResponseEntity<?> getStudentsByAnAndGrupa(@PathVariable("an") int an,@PathVariable("grupa") String grupa) {
        try {
            List<DetaliiStudent> detaliiStudents = detaliiStudentService.findStudentsByAnAndGrupa(an,grupa);
            return new ResponseEntity<>(detaliiStudents, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

        }
    }
    @CrossOrigin
    @RequestMapping(value = "/an={an}/grupa={grupa}/specializare={specializare}", method = {RequestMethod.GET})
    public ResponseEntity<?> getStudentsByAll(@PathVariable("an") String an,@PathVariable("grupa") String grupa,@PathVariable("specializare") String specializare) {
        try {
            List<DetaliiStudent> detaliiStudents = detaliiStudentService.findStudentsByAll(Integer.parseInt(an),grupa,specializare);
            return new ResponseEntity<>(detaliiStudents, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

        }
    }
}
