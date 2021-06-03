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
            throw new Exception("There are no materials for that discipline with that id");
        }
    }

    public List<Materiale> getAllDataSpecific(int id_disciplina, String tip) throws Exception {
        List<Materiale> materiale = materialeRepository.getAllData(id_disciplina, tip);
        if (materiale.size() > 0) {
            return materiale;
        } else {
            throw new Exception("There are no discipline with that id");
        }

    }

    public List<String> getAllPaths(int id_disciplina, String tip) throws Exception {
        List<String> paths = materialeRepository.getAllPaths(id_disciplina, tip);
        if (paths.size() > 0) {
            return paths;
        } else {
            throw new Exception("There are no discipline with that id");
        }

    }

    public List<String> getAllDescriptions(int id_disciplina, String tip) throws Exception {
        List<String> paths = materialeRepository.getAllDescriptions(id_disciplina, tip);
        if (paths.size() > 0) {
            return paths;
        } else {
            throw new Exception("There are no discipline with that id");
        }

    }

    public List<String> getLinkTitle(int id_disciplina, String tip) throws Exception {
        List<String> paths = materialeRepository.getTitles(id_disciplina, tip);
        if (paths.size() > 0) {
            return paths;
        } else {
            throw new Exception("There are no discipline with that id");
        }
    }

    public void addLink(Materiale newLink) throws Exception {
        try {
            materialeRepository.save(newLink);
        }catch (Exception ex)
        {
            throw new Exception("Save was not successfully!");
        }
    }

    public List<Materiale> getSelectedData(int id_disciplina,String tip) throws Exception {
       List<Materiale> materiale=materialeRepository.getSelectedData(id_disciplina,tip);
        if(materiale.size()> 0) {
           return materiale;
        }else
        {
            throw new Exception("Save was not successfully!");
        }
    }
}
