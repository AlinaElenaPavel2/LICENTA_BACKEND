package com.licenta.aplicatie.Service.SituatieScolara;

import com.licenta.aplicatie.Models.SituatieScolara.Recuperare;
import com.licenta.aplicatie.Repository.SituatieScolara.RecuperareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecuperareService {
    @Autowired
    RecuperareRepository recuperareRepository;

    public List<Recuperare> getCereri(int id_disciplina){
        return  recuperareRepository.getCereri(id_disciplina);
    }

    public List<Recuperare> getCereriByGrupa(int id_disciplina,String grupa){
        return  recuperareRepository.getCereriByGrupa(id_disciplina,grupa);
    }

    public Recuperare setProfResponse(int id,String response){
       Optional<Recuperare> recuperare=recuperareRepository.findById(id);
       if(recuperare.isPresent())
       {
           recuperare.get().setAccept(response);
           return  recuperare.get();
       }
       return null;
    }

    public void adaugareCerereRecuperare(Recuperare recuperare)
    {
        recuperareRepository.save(recuperare);
    }

    public List<Recuperare> cereriAcceptate(int id_disciplina, String grupa,String data , String accept)
    {
       return recuperareRepository.cereriAcceptate(id_disciplina,grupa,data,accept);
    }
}
