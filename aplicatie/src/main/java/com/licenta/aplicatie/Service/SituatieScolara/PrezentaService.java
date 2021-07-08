package com.licenta.aplicatie.Service.SituatieScolara;

import com.licenta.aplicatie.Models.SituatieScolara.Prezenta;
import com.licenta.aplicatie.Repository.SituatieScolara.PrezentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PrezentaService {
    @Autowired
    PrezentaRepository prezentaRepository;

    public List<Prezenta> getPrezente(int id_discplina) throws Exception {
        List<Prezenta> prezente=prezentaRepository.findPrezente(id_discplina);
        if(prezente.size()>0)
        {
            return prezente;
        }else
        {
            throw new Exception("There is no presence for this discipline");
        }
    }


    public List<Prezenta> getPrezenteByStudent(int id_student) throws Exception {
        List<Prezenta> prezente=prezentaRepository.findPrezenteByStudent(id_student);
        if(prezente.size()>0)
        {
            return prezente;
        }else
        {
            throw new Exception("There is no presence for this discipline");
        }
    }

    public List<Prezenta> getPrezenteByLaborator(int laborator) throws Exception {
        List<Prezenta> prezente=prezentaRepository.findPrezenteByLaborator(laborator);
        if(prezente.size()>0)
        {
            return prezente;
        }else
        {
            throw new Exception("There is no presence for this discipline");
        }
    }

    public List<Prezenta> getPrezente(int id_disciplina,int id_student) throws Exception {
        List<Prezenta> prezente=prezentaRepository.findPrezente(id_disciplina,id_student);
        if(prezente.size()>0)
        {
            return prezente;
        }else
        {
            throw new Exception("There is no presence for this discipline");
        }
    }

    public List<Prezenta> getPrezenteSecond(int id_disciplina,int id_student) throws Exception {
        List<Prezenta> prezente=prezentaRepository.findPrezenteByStudent(id_student);
        List<Prezenta> prezFilter=new ArrayList<>();
        System.out.println(prezente.size());
        for (Prezenta p:prezente
             ) {
            System.out.println(p.toString());
            if(p.getId_disciplina()==id_disciplina )
            {
                prezFilter.add(p);
            }
        }
        System.out.println(prezFilter.size());

        if(prezFilter.size()>0)
        {
            return prezFilter;
        }else
        {
            throw new Exception("There is no presence for this discipline");
        }
    }

    public Prezenta addPrezenta(Prezenta newPrezenta) throws Exception {
//        prezentaRepository.save(newPrezenta);
        System.out.println(newPrezenta);
        prezentaRepository.adaugarePrezenta(newPrezenta.getId_disciplina(),newPrezenta.getId_student(),newPrezenta.getLaborator(),newPrezenta.getData(),newPrezenta.getPrezenta());

        return newPrezenta;
    }
}
