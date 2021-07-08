package com.licenta.aplicatie.Service.Users;

import com.licenta.aplicatie.Models.Users.Student;
import com.licenta.aplicatie.Repository.Users.StudentRepository;
import com.licenta.aplicatie.Repository.Users.UserRepository;
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

    public List<Student> getDetaliiStudent() {
        return studentRepository.findAll();
    }

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
//-------------------------------------

    public List<Student> findStudentsByAn(int an) throws Exception {
        List<Student> detaliiStudents = new ArrayList<>();
        List<Optional<Student>> studentsFound = studentRepository.findStudentByAn(an);
        for (Optional<Student> student : studentsFound
        ) {
            if (student.isPresent()) {
                detaliiStudents.add(student.get());
            }
        }
        if (detaliiStudents.size() > 0) {
            return detaliiStudents;
        } else {
            throw new Exception("There are no student in this year");
        }
    }

    public List<Student> findStudentsByGrupa(String grupa) throws Exception {
        List<Student> detaliiStudents = new ArrayList<>();
        List<Optional<Student>> studentsFound = studentRepository.findStudentByGrupa(grupa);
        for (Optional<Student> student : studentsFound
        ) {
            if (student.isPresent()) {
                detaliiStudents.add(student.get());
            }
        }
        if (detaliiStudents.size() > 0) {
            return detaliiStudents;
        } else {
            throw new Exception("This group does not exist");
        }
    }
    public List<Student> findStudentsBySpecializare(String specializare) throws Exception {
        List<Student> detaliiStudents = new ArrayList<>();
        List<Optional<Student>> studentsFound = studentRepository.findStudentsBySpecializare(specializare);
        for (Optional<Student> student : studentsFound
        ) {
            if (student.isPresent()) {
                detaliiStudents.add(student.get());
            }
        }
        if (detaliiStudents.size() > 0) {
            return detaliiStudents;
        } else {
            throw new Exception("This specialization does not exist");
        }
    }

    public List<Student> findStudentsBySpecializareAndGrupa(String specializare,String grupa) throws Exception {
        List<Student> detaliiStudents = new ArrayList<>();
        List<Optional<Student>> studentsFound = studentRepository.findStudentsByGrupaAndSpecializare(grupa,specializare);
        for (Optional<Student> student : studentsFound
        ) {
            if (student.isPresent()) {
                detaliiStudents.add(student.get());
            }
        }
        if (detaliiStudents.size() > 0) {
            return detaliiStudents;
        } else {
            throw new Exception("This specialization does not exist");
        }
    }


    public List<Student> findStudentsBySpecializareAndAn(String specializare,int an) throws Exception {
        List<Student> detaliiStudents = new ArrayList<>();
        List<Optional<Student>> studentsFound = studentRepository.findStudentsByYearAndSpecializare(an,specializare);
        for (Optional<Student> student : studentsFound
        ) {
            if (student.isPresent()) {
                detaliiStudents.add(student.get());
            }
        }
        if (detaliiStudents.size() > 0) {
            return detaliiStudents;
        } else {
            throw new Exception("This specialization does not exist");
        }
    }
    public List<Student> findStudentsByAnAndGrupa(int an,String grupa) throws Exception {
        List<Student> detaliiStudents = new ArrayList<>();
        List<Optional<Student>> studentsFound = studentRepository.findStudentsByYearAndGrupa(an,grupa);
        for (Optional<Student> student : studentsFound
        ) {
            if (student.isPresent()) {
                detaliiStudents.add(student.get());
            }
        }
        if (detaliiStudents.size() > 0) {
            return detaliiStudents;
        } else {
            throw new Exception("This specialization does not exist");
        }
    }

    public List<Student> findStudentsByAll(int an,String grupa,String specializare) throws Exception {
        List<Student> detaliiStudents = new ArrayList<>();
        List<Optional<Student>> studentsFound = studentRepository.findStudentsByAllCriterias(an,grupa,specializare);
        for (Optional<Student> student : studentsFound
        ) {
            if (student.isPresent()) {
                detaliiStudents.add(student.get());
            }
        }
        if (detaliiStudents.size() > 0) {
            return detaliiStudents;
        } else {
            throw new Exception("This specialization does not exist");
        }
    }
}
