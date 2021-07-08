package com.licenta.aplicatie.Service.Programa;

import com.licenta.aplicatie.Models.Programa.Disciplina;
import com.licenta.aplicatie.Repository.Programa.DisciplinaRepository;
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
        List<Disciplina> gasite = new ArrayList<>();
        List<Disciplina> discipline = disciplinaRepository.findDisciplinaByIdTitular(id_titular);
        for (Disciplina dis : discipline
        ) {
            gasite.add(dis);
        }
        if (gasite.size() > 0) {
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

    public List<Integer> getIdDiscipline(String titlu) throws Exception {
        Optional<List<Integer>> credite = disciplinaRepository.findIdByIdTitlu(titlu);
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
        Optional<Disciplina> disciplina = disciplinaRepository.findById(id);

        if (disciplina.isPresent()) {
            return disciplina.get();
        } else {
            throw new Exception("Disciplina not found");
        }
    }

    public Disciplina getDisciplinaByTitlu(String titlu) throws Exception {
        Optional<Disciplina> disciplina = disciplinaRepository.findDisciplinaByName(titlu);

        if (disciplina.isPresent()) {
            return disciplina.get();
        } else {
            throw new Exception("Disciplina not found");
        }
    }

    public String getAbreviereByTitlu(String titlu) throws Exception {
        Optional<String> disciplina = disciplinaRepository.findAbreviereByTitlu(titlu);

        if (disciplina.isPresent()) {
            return disciplina.get();
        } else {
            throw new Exception("Abreviere not found");
        }
    }

    public String getTitluByAbreviere(String abreviere) throws Exception {
        Optional<String> disciplina = disciplinaRepository.findTitluByAbreviere(abreviere);

        if (disciplina.isPresent()) {
            return disciplina.get();
        } else {
            throw new Exception("Disciplina not found");
        }
    }

    public List<Disciplina> getAllDisciplines() throws Exception {
        List<Disciplina> discipline = disciplinaRepository.findAll();
        if (discipline.size() > 0) {
            return discipline;
        } else {
            throw new Exception("There are no disciplines");
        }
    }

    public String getAbreviere(String titu) throws Exception {
        Optional<String> disciplina = disciplinaRepository.findAbreviereByTitlu(titu);
        if (disciplina.isPresent()) {
            return disciplina.get();
        } else {
            throw new Exception("No discipline found");
        }
    }

    public String gettitluByAbreviere(String abreviere) throws Exception {
        Optional<String> disciplina = disciplinaRepository.findTitluByAbreviere(abreviere);
        if (disciplina.isPresent()) {
            return disciplina.get();
        } else {
            throw new Exception("No discipline found");
        }
    }

    public List<Disciplina> getDisciplina(String abreviere) throws Exception {
        List<Optional<Disciplina>> discipline = disciplinaRepository.findDisciplinaByAbreviere(abreviere);
        List<Disciplina> found = new ArrayList<>();
        for (Optional<Disciplina> dis : discipline
        ) {
            if (dis.isPresent()) {
                found.add(dis.get());
            }
        }
        if (found.size() > 0) {
            return found;
        } else {
            throw new Exception("No discipline found");
        }
    }
}
