package com.licenta.aplicatie.Service.SituatieScolara;

import com.licenta.aplicatie.Models.SituatieScolara.Catalog;
import com.licenta.aplicatie.Models.SituatieScolara.Evaluare;
import com.licenta.aplicatie.Repository.SituatieScolara.CatalogRepository;
import com.licenta.aplicatie.Repository.SituatieScolara.EvaluareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CatalogService {
    @Autowired
    CatalogRepository catalogRepository;
    @Autowired
    EvaluareRepository evaluareRepository;

    public Catalog getMarks(int id_disciplina, int id_student) throws Exception {
        Optional<Catalog> catalogOp = catalogRepository.findMark(id_disciplina, id_student);
        if (catalogOp.isPresent()) {
            Catalog catalog = catalogOp.get();
//            if (catalog.getExamen() == null) {
//                catalog.setExamen(0);
//            }
//            if (catalog.getPartial() == null) {
//                catalog.setPartial(0);
//            }
//            if (catalog.getLaborator() == null) {
//                catalog.setLaborator(0);
//            }
//            if (catalog.getProiect() == null) {
//                catalog.setProiect(0);
//            }
            return catalog;
        } else {
            throw new Exception("The discipline does not exist");
        }
    }

    public float getFinalMark(int id_disciplina, int id_student) throws Exception {
        Optional<Catalog> cat = catalogRepository.findMark(id_disciplina, id_student);
        Optional<Evaluare> eval = evaluareRepository.findProcents(id_disciplina);
        if (eval.isPresent()) {
            Evaluare evaluare = eval.get();
            if (cat.isPresent()) {
                Catalog catalog = cat.get();
                float nota = 0;
                float examen = 0;
                float partial = 0;
                float laborator = 0;
                float proiect = 0;
                if (evaluare.getPondere_examen() != null & catalog.getExamen() != null) {
                    examen = evaluare.getPondere_examen() * catalog.getExamen();
                }
                if (evaluare.getPondere_partial() != null & catalog.getPartial() != null) {
                    partial = evaluare.getPondere_partial() * catalog.getPartial();
                }
                if (evaluare.getPondere_lab() != null & catalog.getLaborator() != null) {
                    laborator = evaluare.getPondere_lab() * catalog.getLaborator();
                }
                if (evaluare.getPondere_proiect() != null & catalog.getProiect() != null) {
                    proiect = evaluare.getPondere_proiect() * catalog.getProiect();
                }
                nota = examen + partial + laborator + proiect;
                return nota / 100;
            } else {
                throw new Exception("The student for this discipline does not exist");
            }
        } else {
            throw new Exception("The discipline does not exist");
        }
    }

}
