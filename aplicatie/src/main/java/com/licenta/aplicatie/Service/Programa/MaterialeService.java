package com.licenta.aplicatie.Service.Programa;

import com.licenta.aplicatie.Models.Programa.Materiale;
import com.licenta.aplicatie.Repository.Programa.LaboratorRepository;
import com.licenta.aplicatie.Repository.Programa.MaterialeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MaterialeService {
    @Autowired
    MaterialeRepository materialeRepository;

    public List<Materiale> getAllData(int id_disciplina) throws Exception {
        List<Materiale> materiale = materialeRepository.getAllData(id_disciplina);
        if (materiale.size() > 0) {
            return materiale;
        } else {
            throw new Exception("There are no discipline with that id");
        }
    }

    public List<Materiale> getAllDataSpecific(int id_disciplina, String tip) throws Exception {
        if (!(tip.equals("Curs") || tip.equals("Laborator") || tip.equals("Auxiliare"))) {
            List<Materiale> materiale = materialeRepository.getAllData(id_disciplina, tip);
            if (materiale.size() > 0) {
                return materiale;
            } else {
                throw new Exception("There are no discipline with that id");
            }
        } else {
            throw new Exception("There are component for the discipline");
        }
    }

    public List<String> getAllPaths(int id_disciplina, String tip) throws Exception {
        if (!(tip.equals("Curs") || tip.equals("Laborator") || tip.equals("Auxiliare"))) {
            List<String> paths = materialeRepository.getAllPaths(id_disciplina, tip);
            if (paths.size() > 0) {
                return paths;
            } else {
                throw new Exception("There are no discipline with that id");
            }
        } else {
            throw new Exception("There are component for the discipline");
        }
    }
    public List<String> getAllDescriptions(int id_disciplina, String tip) throws Exception {
        if (!(tip.equals("Curs") || tip.equals("Laborator") || tip.equals("Auxiliare"))) {
            List<String> paths = materialeRepository.getAllPaths(id_disciplina, tip);
            if (paths.size() > 0) {
                return paths;
            } else {
                throw new Exception("There are no discipline with that id");
            }
        } else {
            throw new Exception("There are component for the discipline");
        }
    }
}
