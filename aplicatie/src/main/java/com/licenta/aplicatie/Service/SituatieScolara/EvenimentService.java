package com.licenta.aplicatie.Service.SituatieScolara;

import com.licenta.aplicatie.Models.SituatieScolara.Eveniment;
import com.licenta.aplicatie.Repository.SituatieScolara.EvenimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EvenimentService {
    @Autowired
    EvenimentRepository evenimentRepository;

    public List<Eveniment> getEvenimente(int id_disciplina) throws Exception {
        List<Eveniment> evenimente = evenimentRepository.findEvenimenteByDisciplina(id_disciplina);
        if (evenimente.size() > 0) {
            return evenimente;
        } else {
            throw new Exception("There are no eveniment for this discipline");
        }
    }

    public void saveEveniment(Eveniment newEveniment) throws Exception {
        try {
            evenimentRepository.addEveniment(newEveniment.getId_disciplina(), newEveniment.getData(), newEveniment.getTitlu(), newEveniment.getDescriere());
        } catch (Exception ex) {
            throw new Exception("Evenimentul nu a fost adaugat");
        }
    }

    public void deleteEveniment(Eveniment newEveniment) throws Exception {
        try {
            evenimentRepository.deleteEveniment(newEveniment.getId_disciplina(), newEveniment.getData(), newEveniment.getTitlu(), newEveniment.getDescriere());
        } catch (Exception ex) {
            System.out.println(ex.toString());
            throw new Exception("Evenimentul nu a fost sters");
        }
    }

    public Eveniment getExam(int id_disciplina) throws Exception {
        List<Eveniment> evenimente = evenimentRepository.findEvenimenteByDisciplina(id_disciplina);
        if (evenimente.size() > 0) {
            for (Eveniment ev : evenimente
            ) {
                System.out.println(ev.toString());
                if (ev.getTitlu().contains("EXAMEN")) {
                    return ev;
                } else {
                    return null;
                }
            }
        } else {
            throw new Exception("There are no eveniment for this discipline");
        }
        return null;
    }

    public Eveniment getEveniment(int id_disciplina) throws Exception {
        Optional<Eveniment> eveniment = evenimentRepository.getEveniment(id_disciplina);
        if (eveniment.isPresent()) {
            return eveniment.get();
        } else {
            throw new Exception("There are no eveniment for this discipline");
        }
    }
}
