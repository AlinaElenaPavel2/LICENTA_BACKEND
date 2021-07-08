package com.licenta.aplicatie.Service.SituatieScolara;

import com.licenta.aplicatie.Models.SituatieScolara.SituatieScolara;
import com.licenta.aplicatie.Repository.SituatieScolara.SituatieScolaraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SituatieScolaraService {
    @Autowired
    SituatieScolaraRepository situatieScolaraRepository;

    public int getMedieByDisciplina(int id_disciplina, int id_student) throws Exception {
        Optional<Integer> medie=situatieScolaraRepository.findMedieByDisciplina(id_disciplina,id_student);
        if (medie.isPresent())
        {
            return medie.get();
        }else
        {
            throw  new Exception("It is no student for this discipline");
        }
    }

    public List<Integer> getMedii(int id_student) throws Exception {
        List<Integer> medii=situatieScolaraRepository.findMedii(id_student);
        if (medii.size()>0)
        {
            return medii;
        }else
        {
            throw  new Exception("It is no student for this discipline");
        }
    }

    public SituatieScolara getMediiForDisciplina(int id_student,int id_disciplina) throws Exception {
        List<SituatieScolara> medii=situatieScolaraRepository.findAll();

        for (SituatieScolara medie:medii
             ) {
            if(medie.getId_student()==id_student && medie.getId_disciplina() == id_disciplina)
            {
                return medie;
            }
        }
//        for (SituatieScolara s:medii
//             ) {
//            System.out.println("Service");
//            System.out.println(s.getId_disciplina());
//            System.out.println(s.getMedie());
//        }
        return null;
    }

    public List<Integer> getDiscipline(int id_student) throws Exception {
        List<Integer> discipline=situatieScolaraRepository.findDiscipline(id_student);
        if (discipline.size()>0)
        {
            return discipline;
        }else
        {
            throw  new Exception("It is no student for this discipline");
        }
    }
}
