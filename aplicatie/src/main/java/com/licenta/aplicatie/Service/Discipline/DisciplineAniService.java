package com.licenta.aplicatie.Service.Discipline;

import com.licenta.aplicatie.Repository.Discipline.DisciplinaRepository;
import com.licenta.aplicatie.Repository.Discipline.DisciplineAniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DisciplineAniService {

    @Autowired
    DisciplineAniRepository disciplineAniRepository;

    public List<Integer> getDiscipleByYear(int an) throws Exception {
        List<Integer> found = new ArrayList<>();
        List<Optional<Integer>> discipline = disciplineAniRepository.findDisciplinebyYear(an);
        for (Optional<Integer> d : discipline
        ) {
            if (d.isPresent()) {
                found.add(d.get());
            }
        }

        if (found.size() > 0) {
            return found;
        } else {
            throw new Exception("Discipline not found");
        }
    }

    public List<Integer> getDiscipleByYear(int an, int semestru) throws Exception {
        List<Integer> found = new ArrayList<>();
        List<Optional<Integer>> discipline = disciplineAniRepository.findDisciplinaBySemester(an, semestru);
        for (Optional<Integer> d : discipline
        ) {
            if (d.isPresent()) {
                found.add(d.get());
            }
        }

        if (found.size() > 0) {
            return found;
        } else {
            throw new Exception("Discipline not found");
        }
    }

    public List<Integer> getDiscipleAll(int an, int semestru, String specializare) throws Exception {
        List<Integer> found = new ArrayList<>();
        List<Optional<Integer>> discipline = disciplineAniRepository.findDisciplinaByAll(an, semestru, specializare);
        for (Optional<Integer> d : discipline
        ) {
            if (d.isPresent()) {
                found.add(d.get());
            }
        }

        if (found.size() > 0) {
            return found;
        } else {
            throw new Exception("Discipline not found");
        }
    }

}
