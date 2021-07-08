package com.licenta.aplicatie.Controller.Users;

import com.licenta.aplicatie.Models.Users.Student;
import com.licenta.aplicatie.Service.Users.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/licenta/students")
public class StudentController {
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


//    --------------------



    @CrossOrigin
    @RequestMapping(value = "/an={an}", method = {RequestMethod.GET})
    public ResponseEntity<?> getStudentsByYear(@PathVariable("an") int an) {
        try {
            List<Student> detaliiStudents = studentService.findStudentsByAn(an);
            return new ResponseEntity<>(detaliiStudents, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @CrossOrigin
    @RequestMapping(value = "/grupa={grupa}", method = {RequestMethod.GET})
    public ResponseEntity<?> getStudentsByGrupa(@PathVariable("grupa") String grupa) {
        try {
            List<Student> detaliiStudents = studentService.findStudentsByGrupa(grupa);
            return new ResponseEntity<>(detaliiStudents, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

        }
    }
    @CrossOrigin
    @RequestMapping(value = "/specializare={specializare}", method = {RequestMethod.GET})
    public ResponseEntity<?> getStudentsBySpecializare(@PathVariable("specializare") String specializare) {
        try {
            List<Student> detaliiStudents = studentService.findStudentsBySpecializare(specializare);
            return new ResponseEntity<>(detaliiStudents, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @CrossOrigin
    @RequestMapping(value = "/specializare={specializare}/grupa={grupa}", method = {RequestMethod.GET})
    public ResponseEntity<?> getStudentsBySpecializareAndGrupa(@PathVariable("specializare") String specializare,@PathVariable("grupa") String grupa) {
        try {
            List<Student> detaliiStudents = studentService.findStudentsBySpecializareAndGrupa(specializare,grupa);
            return new ResponseEntity<>(detaliiStudents, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @CrossOrigin
    @RequestMapping(value = "/an={an}/specializare={specializare}", method = {RequestMethod.GET})
    public ResponseEntity<?> getStudentsBySpecializareAndAn(@PathVariable("specializare") String specializare,@PathVariable("an") int an) {
        try {
            List<Student> detaliiStudents = studentService.findStudentsBySpecializareAndAn(specializare,an);
            return new ResponseEntity<>(detaliiStudents, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @CrossOrigin
    @RequestMapping(value = "/an={an}/grupa={grupa}", method = {RequestMethod.GET})
    public ResponseEntity<?> getStudentsByAnAndGrupa(@PathVariable("an") int an,@PathVariable("grupa") String grupa) {
        try {
            List<Student> detaliiStudents = studentService.findStudentsByAnAndGrupa(an,grupa);
            return new ResponseEntity<>(detaliiStudents, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @CrossOrigin
    @RequestMapping(value = "/an={an}/grupa={grupa}/specializare={specializare}", method = {RequestMethod.GET})
    public ResponseEntity<?> getStudentsByAll(@PathVariable("an") String an,@PathVariable("grupa") String grupa,@PathVariable("specializare") String specializare) {
        try {
            List<Student> detaliiStudents = studentService.findStudentsByAll(Integer.parseInt(an),grupa,specializare);
            return new ResponseEntity<>(detaliiStudents, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

        }
    }
}
