package com.licenta.aplicatie.Service.Student;

import com.licenta.aplicatie.Models.Studenti.DetaliiStudent;
import com.licenta.aplicatie.Repository.Student.DetaliiStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetaliiStudentService {

    @Autowired
    DetaliiStudentRepository detaliiStudentRepository;

    public List<DetaliiStudent> getDetaliiStudent() {
        return detaliiStudentRepository.findAll();
    }

    public List<DetaliiStudent> findStudentsByAn(int an) throws Exception {
        List<DetaliiStudent> detaliiStudents = new ArrayList<>();
        List<Optional<DetaliiStudent>> studentsFound = detaliiStudentRepository.findStudentByAn(an);
        for (Optional<DetaliiStudent> student : studentsFound
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

    public List<DetaliiStudent> findStudentsByGrupa(String grupa) throws Exception {
        List<DetaliiStudent> detaliiStudents = new ArrayList<>();
        List<Optional<DetaliiStudent>> studentsFound = detaliiStudentRepository.findStudentByGrupa(grupa);
        for (Optional<DetaliiStudent> student : studentsFound
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

    public List<DetaliiStudent> findStudentsBySpecializare(String specializare) throws Exception {
        List<DetaliiStudent> detaliiStudents = new ArrayList<>();
        List<Optional<DetaliiStudent>> studentsFound = detaliiStudentRepository.findStudentsBySpecializare(specializare);
        for (Optional<DetaliiStudent> student : studentsFound
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
