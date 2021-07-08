package com.licenta.aplicatie.Service.SituatieScolara;

import com.licenta.aplicatie.Models.SituatieScolara.Restanta;
import com.licenta.aplicatie.Repository.SituatieScolara.RestantaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RestantaService {
    @Autowired
    RestantaRepository restantaRepository;

    public List<Restanta> getRestForDiscipline(int id_discipline) throws Exception {
        List<Restanta> restante = restantaRepository.findRestForDiscipline(id_discipline);
        if (restante.size() > 0) {
            return restante;
        } else {
            throw new Exception("There are no re-exams for this discipline");
        }
    }

    public List<Restanta> getRestForDate(Date date) throws Exception {
        List<Restanta> restante = restantaRepository.findRestForDate(date);
        if (restante.size() > 0) {
            return restante;
        } else {
            throw new Exception("There are no re-exams for this discipline");
        }
    }

    public List<Restanta> getRestForStudents(int id_student) throws Exception {
        List<Restanta> restante = restantaRepository.findRestForStudents(id_student);
        if (restante.size() > 0) {
            return restante;
        } else {
            throw new Exception("There are no re-exams for this discipline");
        }
    }

    public List<Integer> getListReex(int id_student) throws Exception {
        int reexaminari = restantaRepository.findReexamniariForStudents(id_student, "reexaminari");
        int rereexaminari = restantaRepository.findReexamniariForStudents(id_student, "re-reexaminari");
        List<Integer> count = new ArrayList<>();
        count.add(reexaminari);
        count.add(rereexaminari);
        return count;
    }
}
