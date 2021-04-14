package com.licenta.aplicatie.Controller.Student;

import com.licenta.aplicatie.Models.Studenti.Student;
import com.licenta.aplicatie.Service.Student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/licenta/students")
public class StudenController {

    @Autowired
    StudentService studentService;

    @CrossOrigin
    @RequestMapping(value = "/fullname={fullname}", method = {RequestMethod.GET})
    public ResponseEntity<?> getStudentByFullName(@PathVariable("fullname") String name) {
        try {
            Student student = studentService.getStudentByName(name);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/firstname={firstname}", method = {RequestMethod.GET})
    public ResponseEntity<?> getStudentByFirstName(@PathVariable("firstname") String firstname) {
        try {
            List<Student> students = studentService.getStudentsByFirstName(firstname);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/lastname={lastname}", method = {RequestMethod.GET})
    public ResponseEntity<?> getStudentByLastName(@PathVariable("lastname") String lastname) {
        try {
            List<Student> students = studentService.getStudentByLastName(lastname);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin
    @RequestMapping(value = "/email={email}", method = {RequestMethod.GET})
    public ResponseEntity<?> getStudentByEmail(@PathVariable("email") String email) {
        try {
            Student student = studentService.getStudentByEmail(email);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/student/email={email}", method = {RequestMethod.GET})
    public ResponseEntity<?> getStudentIdByEmail(@PathVariable("email") String email) {
        try {
            int student_id = studentService.getStudentIdByEmail(email);
            return new ResponseEntity<>(student_id, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/student/fullName={fullname}", method = {RequestMethod.GET})
    public ResponseEntity<?> getStudentIdByName(@PathVariable("fullname") String name) {
        try {
            int student_id = studentService.getStudentIdByName(name);
            return new ResponseEntity<>(student_id, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin
    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> getStudentById(@PathVariable("id") int id) {
        try {
            Student student = studentService.getStudentById(id);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
