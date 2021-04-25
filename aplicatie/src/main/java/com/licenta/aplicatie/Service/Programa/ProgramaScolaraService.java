package com.licenta.aplicatie.Service.Programa;


import com.licenta.aplicatie.Models.Programa.Disciplina;
import com.licenta.aplicatie.Models.Programa.ProgramaScolara;
import com.licenta.aplicatie.Repository.Programa.ProgramaScolaraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProgramaScolaraService {
    @Autowired
    ProgramaScolaraRepository programaScolaraRepository;

    @Autowired
    DisciplinaService disciplinaService;

    public List<Disciplina> getDisciplineByAn(String programStudii,int an) throws Exception {
        List<Integer> disciplineIds = programaScolaraRepository.findDisciplinesByAn(programStudii,an);
        List<Disciplina> discipline = new ArrayList<>();
        if (disciplineIds.size() > 0) {
            for (Integer id_disciplina:disciplineIds
                 ) {
                discipline.add(disciplinaService.getDisciplina(id_disciplina));
            }
            return discipline;
        } else {
            throw new Exception("No disciplines found for this year");
        }
    }

    public List<Disciplina> getDisciplineBySpecializare(String programStudii,String specializare) throws Exception {
        List<Integer> disciplineIds = programaScolaraRepository.findDisciplinesBySpecializare(programStudii,specializare);
        List<Disciplina> discipline = new ArrayList<>();
        if (disciplineIds.size() > 0) {
            for (Integer id_disciplina:disciplineIds
            ) {
                discipline.add(disciplinaService.getDisciplina(id_disciplina));
            }
            return discipline;
        } else {
            throw new Exception("No disciplines found for this year");
        }
    }

    public List<Disciplina> getDisciplinesBySpecializareAndAn(String programStudii,String specializare,int an) throws Exception {
        List<Integer> disciplineIds = programaScolaraRepository.findDisciplinesBySpecializareAndAn(programStudii,specializare,an);
        List<Disciplina> discipline = new ArrayList<>();
        if (disciplineIds.size() > 0) {
            for (Integer id_disciplina:disciplineIds
            ) {
                discipline.add(disciplinaService.getDisciplina(id_disciplina));
            }
            return discipline;
        } else {
            throw new Exception("No disciplines found for this year");
        }
    }

    public List<Disciplina> getDisciplinesByAnAndSemestru(String programStudii,int an,int semestru) throws Exception {
        List<Integer> disciplineIds = programaScolaraRepository.findDisciplinesByAnAndSemestru(programStudii,an,semestru);
        List<Disciplina> discipline = new ArrayList<>();
        if (disciplineIds.size() > 0) {
            for (Integer id_disciplina:disciplineIds
            ) {
                discipline.add(disciplinaService.getDisciplina(id_disciplina));
            }
            return discipline;
        } else {
            throw new Exception("No disciplines found for this year");
        }
    }

    public List<Disciplina> getDiscipline(String programStudii,String specializare,int an,int semestru) throws Exception {
        List<Integer> disciplineIds = programaScolaraRepository.findDisciplines(programStudii,specializare,an,semestru);
        List<Disciplina> discipline = new ArrayList<>();
        if (disciplineIds.size() > 0) {
            for (Integer id_disciplina:disciplineIds
            ) {
                discipline.add(disciplinaService.getDisciplina(id_disciplina));
            }
            return discipline;
        } else {
            throw new Exception("No disciplines found for this year");
        }
    }
}
