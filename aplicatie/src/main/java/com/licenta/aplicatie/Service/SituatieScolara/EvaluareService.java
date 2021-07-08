package com.licenta.aplicatie.Service.SituatieScolara;

import com.licenta.aplicatie.Models.SituatieScolara.Evaluare;
import com.licenta.aplicatie.Repository.SituatieScolara.EvaluareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class EvaluareService {
    @Autowired
    EvaluareRepository evaluareRepository;

    public Evaluare getProcents(int id_disciplina) throws Exception {
        Optional<Evaluare> evaluare=evaluareRepository.findProcents(id_disciplina);
        if(evaluare.isPresent())
        {
            return evaluare.get();
        }else
        {
            throw  new Exception("This discipline does not exist!");
        }
    }
}
