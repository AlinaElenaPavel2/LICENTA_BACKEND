package com.licenta.aplicatie.Service.Discipline;

import com.licenta.aplicatie.Models.Discipline.Disciplina;
import com.licenta.aplicatie.Models.Profesor;
import com.licenta.aplicatie.Repository.Discipline.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DisciplinaService {
    @Autowired
    DisciplinaRepository disciplinaRepository;

    public Disciplina getDisciplinebyYear(String titlu) throws Exception {
        Optional<Disciplina> disciplina = disciplinaRepository.findDisciplinaByName(titlu);
        if (disciplina.isPresent()) {
            return disciplina.get();
        } else {
            throw new Exception("Disciplina not found");
        }
    }

    public List<Disciplina> getDisciplinaByIdTitular(int id_titular) throws Exception {
        List<Disciplina> gasite=new ArrayList<>();
        List<Optional<Disciplina>> discipline = disciplinaRepository.findDisciplinaByIdTitular(id_titular);
        for (Optional<Disciplina> dis : discipline
        ) {
            if (dis.isPresent()) {
                gasite.add(dis.get());
            }
        }
        if (gasite.size()>0) {
            return gasite;
        } else {
            throw new Exception("Discipline not found");
        }
    }

    public int getCredite(String titlu) throws Exception {
        Optional<Integer> credite = disciplinaRepository.findCrediteByTitlu(titlu);
        if (credite.isPresent()) {
            return credite.get();
        } else {
            throw new Exception("Disciplina not found");
        }
    }
    public int getIdDiscipline(String titlu) throws Exception {
        Optional<Integer> credite = disciplinaRepository.findIdByIdTitlu(titlu);
        if (credite.isPresent()) {
            return credite.get();
        } else {
            throw new Exception("Disciplina not found");
        }
    }

    public int getIdDiscipline(int id_titular) throws Exception {
        Optional<Integer> credite = disciplinaRepository.findIdByIdTitular(id_titular);
        if (credite.isPresent()) {
            return credite.get();
        } else {
            throw new Exception("Disciplina not found");
        }

    }

    public Disciplina getDisciplina(int id) throws Exception {
        Optional<Disciplina> disciplina=disciplinaRepository.findById(id);

        if(disciplina.isPresent())
        {
            return disciplina.get();
        }else
        {
            throw new Exception("Disciplina not found");
        }
    }
    public Disciplina getDisciplinaByTitlu(String titlu) throws Exception {
        Optional<Disciplina> disciplina=disciplinaRepository.findDisciplinaByName(titlu);

        if(disciplina.isPresent())
        {
            return disciplina.get();
        }else
        {
            throw new Exception("Disciplina not found");
        }
    }
}
