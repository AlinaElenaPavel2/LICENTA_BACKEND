package com.licenta.aplicatie.Service.SituatieScolara;

import com.licenta.aplicatie.Models.Programa.Disciplina;
import com.licenta.aplicatie.Models.Programa.ProgramaScolara;
import com.licenta.aplicatie.Models.SituatieScolara.Catalog;
import com.licenta.aplicatie.Models.SituatieScolara.Evaluare;
import com.licenta.aplicatie.Models.Users.Student;
import com.licenta.aplicatie.Repository.Programa.DisciplinaRepository;
import com.licenta.aplicatie.Repository.Programa.ProgramaScolaraRepository;
import com.licenta.aplicatie.Repository.SituatieScolara.CatalogRepository;
import com.licenta.aplicatie.Repository.SituatieScolara.EvaluareRepository;
import com.licenta.aplicatie.Repository.Users.StudentRepository;
import com.licenta.aplicatie.Service.Users.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CatalogService {
    @Autowired
    CatalogRepository catalogRepository;
    @Autowired
    EvaluareRepository evaluareRepository;
    @Autowired
    ProgramaScolaraRepository programaScolaraRepository;
    @Autowired
    DisciplinaRepository disciplinaRepository;
    @Autowired
    StudentService studentService;

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

    public HashMap<String, Float> getFinalSituation(int id_student, String programStudii, String specializare, int an) throws Exception {
        List<Integer> disciplinesId = programaScolaraRepository.findDisciplinesBySpecializareAndAn(programStudii, specializare, an);
        System.out.println("Discipline ids");
        System.out.println(disciplinesId);

        HashMap<String, Float> situatie = new HashMap<>();
        for (Integer id : disciplinesId) {
            Disciplina disciplina = disciplinaRepository.findDisciplina(id);
            situatie.put(disciplina.getAbreviere(), this.getFinalMark(id_student, id));
        }
        return situatie;
    }

    public Catalog updateMark(int id_student, int id_diciplina, Catalog newNote) {
        newNote.setId_student(id_student);
        newNote.setId_disciplina(id_diciplina);
        System.out.println(newNote);
        List<Catalog> catalog = catalogRepository.findAll();
        for (Catalog note : catalog
        ) {
            if (note.getId_disciplina() == id_diciplina && note.getId_student() == id_student) {

                if (newNote.getProiect() != null) {
                    note.setProiect(newNote.getProiect());
                }

                if (newNote.getExamen() != null) {
                    note.setExamen(newNote.getExamen());
                }
                if (newNote.getLaborator() != null) {
                    note.setLaborator(newNote.getLaborator());
                }
                if (newNote.getPartial() != null) {
                    note.setPartial(newNote.getPartial());
                }
                return note;
            }
        }
        return null;
    }

    public Map<Integer, Integer> getNoteDisciplina(int id_diciplina, String tip) {
        List<Catalog> catalog = catalogRepository.findAll();
        List<Integer> selected = new ArrayList<>();
        for (Catalog note : catalog
        ) {
            if (note.getId_disciplina() == id_diciplina) {
                switch (tip) {
                    case "examen":
                        selected.add(note.getExamen());
                        break;
                    case "laborator":
                        selected.add(note.getLaborator());
                        break;
                    case "partial":
                        selected.add(note.getPartial());
                        break;
                    case "proiect":
                        selected.add(note.getProiect());
                        break;
                }
            }
        }
        Map<Integer, Integer> counts =frequence(selected);

        return counts;
    }

    public Map<Integer, Integer> frequence(List<Integer> list)
    {
        Map<Integer, Integer> map=new HashMap<>();
        for(int i=0;i<=10;i++)
        {
            int occurrences = Collections.frequency(list, i);
            map.put(i,occurrences);
        }
        return map;
    }

    public Map<Integer, Integer> getAllFinalMark(int id_disciplina) throws Exception {
        List<Integer> finalMarks=new ArrayList<>();
        Disciplina disciplina=disciplinaRepository.findDisciplina(id_disciplina);
        ProgramaScolara pr=programaScolaraRepository.getOne(id_disciplina);
        List<Student> studenti=studentService.findStudentsBySpecializareAndAn(pr.getSpecializare(),pr.getAn());
        for (Student st:studenti
             ) {
            finalMarks.add((int)getFinalMark(id_disciplina,st.getId_student()));
        }
        Map<Integer, Integer> counts =frequence(finalMarks);
        return counts;
    }


}
