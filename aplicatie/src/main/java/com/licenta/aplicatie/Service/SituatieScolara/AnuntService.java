package com.licenta.aplicatie.Service.SituatieScolara;

import com.licenta.aplicatie.Models.SituatieScolara.Anunt;
import com.licenta.aplicatie.Repository.SituatieScolara.AnuntRepository;
import com.licenta.aplicatie.Repository.SituatieScolara.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AnuntService {
    @Autowired
    AnuntRepository anuntRepository;

    public List<Anunt> getAnunturi(int id_disciplina,String grupa) throws Exception {
        System.out.println(id_disciplina);
        List<Anunt> anunturi=anuntRepository.getAnunturi(id_disciplina,grupa);
        System.out.println(anunturi.size());

        if(anunturi.size()>0)
        {
            return anunturi;
        }else{
           return null;
        }
    }
    public void addAnunt(Anunt anunt)
    {
        anuntRepository.save(anunt);
    }

}
