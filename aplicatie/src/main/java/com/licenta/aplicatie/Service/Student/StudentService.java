package com.licenta.aplicatie.Service.Student;

import com.licenta.aplicatie.Models.Studenti.Student;
import com.licenta.aplicatie.Repository.Student.StudentRepository;
import com.licenta.aplicatie.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    StudentRepository studentRepository;

    public Student getStudentByName(String name) throws Exception {
        Optional<Student> student = studentRepository.findStudentByName(name);
        if (student.isPresent()) {
            return student.get();
        } else {
            throw new Exception("Student not found");
        }
    }

    public Student getStudentByEmail(String email) throws Exception {
        Optional<Student> student = studentRepository.findStudentByEmail(email);
        if (student.isPresent()) {
            return student.get();
        } else {
            throw new Exception("Student not found");
        }
    }

    public Student getStudentById(int id) throws Exception {
        Optional<Student> student = studentRepository.findStudentById(id);
        if (student.isPresent()) {
            return student.get();
        } else {
            throw new Exception("Student not found");
        }
    }

    public int getStudentIdByName(String name) throws Exception {
        Optional<Integer> student_id = studentRepository.findStudentIdByName(name);
        if (student_id.isPresent()) {
            return student_id.get();
        } else {
            throw new Exception("Student id not found");
        }
    }

    public int getStudentIdByEmail(String email) throws Exception {
        Optional<Integer> student_id = studentRepository.findStudentIdByEmail(email);
        if (student_id.isPresent()) {
            return student_id.get();
        } else {
            throw new Exception("Student id not found");
        }
    }

    public List<Student> getStudentByLastName(String lastName) throws Exception {
        List<Student> studentsFound = new ArrayList<>();
        List<Student> students = studentRepository.findAll();
        for (Student student : students
        ) {
            if (student.getLastName().equals(lastName)) {
                studentsFound.add(student);
            }
        }

        if (studentsFound.size() > 0) {
            return studentsFound;
        } else {
            throw new Exception("There are no student having this last name");
        }
    }

    public List<Student> getStudentsByFirstName(String firstName) throws Exception {
        List<Student> studentsFound = new ArrayList<>();
        List<Student> students = studentRepository.findAll();
        for (Student student : students
        ) {
            if (student.getFirstName().equals(firstName)) {
                studentsFound.add(student);
            }
        }

        if (studentsFound.size() > 0) {
            return studentsFound;
        } else {
            throw new Exception("There are no student having this first name");
        }
    }

}
