package com.licenta.aplicatie.Service.Programa;

import com.licenta.aplicatie.Models.Programa.Laborator;
import com.licenta.aplicatie.Repository.Programa.LaboratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LaboratorService {
    @Autowired
    LaboratorRepository laboratorRepository;

    public int getLabProfesor(int id_disciplina,String grupa) throws Exception {
        Optional<Integer> id_prof=laboratorRepository.findLabProfesor(id_disciplina,grupa);
        if(id_prof.isPresent())
        {
            return id_prof.get();
        }else
        {
            throw  new Exception("There are no profesor for that group");
        }
    }

    public List<Integer> getLabProfesori(int id_disciplina) throws Exception {
        List<Integer> profesori=laboratorRepository.findLabProfesori(id_disciplina);
        if(profesori.size() >0)
        {
            return profesori;
        }else
        {
            throw  new Exception("There are no profesor for that group");
        }
    }
    public List<Laborator> getLabProfesoriAngGroups(int id_disciplina) throws Exception {
        List<Laborator> profesori=laboratorRepository.findLabProfesoriAndGrupa(id_disciplina);
        if(profesori.size() >0)
        {
            return profesori;
        }else
        {
            throw  new Exception("There are no profesor for that group");
        }
    }

    public List<String> getGroupsByProfesorAndDiscip(int id_disciplina,int id_profesor) throws Exception {
        List<String> grups=laboratorRepository.findgrupaByDiscipAndProf(id_disciplina,id_profesor);
        if(grups.size() >0)
        {
            return grups;
        }else
        {
            throw  new Exception("There are no grups for that discipline and prof");
        }
    }
}
