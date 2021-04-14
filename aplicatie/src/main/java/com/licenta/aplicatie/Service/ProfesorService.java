package com.licenta.aplicatie.Service;

import com.licenta.aplicatie.Models.Profesor;
import com.licenta.aplicatie.Models.Studenti.Student;
import com.licenta.aplicatie.Repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProfesorService {
    @Autowired
    ProfesorRepository profesorRepository;

    public Profesor getProfesorByName(String name) throws Exception {
        Optional<Profesor> profesor = profesorRepository.findProfesorByName(name);
        if (profesor.isPresent()) {
            return profesor.get();
        } else {
            throw new Exception("Profesor not found");
        }
    }

    public Profesor getProfesorByEmail(String email) throws Exception {
        Optional<Profesor> profesor = profesorRepository.findProfesorByEmail(email);
        if (profesor.isPresent()) {
            return profesor.get();
        } else {
            throw new Exception("Profesor not found");
        }
    }

    public Profesor getProfesorById(int id) throws Exception {
        Optional<Profesor> profesor = profesorRepository.findById(id);
        if (profesor.isPresent()) {
            return profesor.get();
        } else {
            throw new Exception("Profesor not found");
        }
    }

    public List<Profesor> getProfesorByFirstName(String firstName) throws Exception {
        List<Profesor> found = new ArrayList<>();
        List<Profesor> profesors = profesorRepository.findAll();
        for (Profesor profesor : profesors
        ) {
            if (profesor.getFirstName().equals(firstName)) {
                found.add(profesor);
            }
        }
        if (found.size() > 0) {
            return found;
        } else {
            throw new Exception("Profesor not found");
        }
    }

}
