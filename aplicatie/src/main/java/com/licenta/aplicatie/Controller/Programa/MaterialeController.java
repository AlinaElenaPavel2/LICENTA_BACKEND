package com.licenta.aplicatie.Controller.Programa;

import com.licenta.aplicatie.Models.Programa.Disciplina;
import com.licenta.aplicatie.Models.Programa.Materiale;
import com.licenta.aplicatie.Models.Users.Profesor;
import com.licenta.aplicatie.Service.Programa.DisciplinaService;
import com.licenta.aplicatie.Service.Programa.MaterialeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/licenta/materiale")
public class MaterialeController {
    @Autowired
    DisciplinaService disciplinaService;
    @Autowired
    MaterialeService materialeService;

    @CrossOrigin
    @RequestMapping(value = "/disciplina={disciplina}/tip={tip}/paths", method = {RequestMethod.GET})
    public ResponseEntity<?> getPathsForDiscipline(@PathVariable("tip") String tip, @PathVariable("disciplina") String disciplina) {
        try {
            Disciplina discip = disciplinaService.getDisciplinaByTitlu(disciplina);
            List<String> paths = materialeService.getAllPaths(discip.getId_disciplina(), tip);
            return new ResponseEntity<>(paths, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/disciplina={disciplina}/tip={tip}/descriere", method = {RequestMethod.GET})
    public ResponseEntity<?> getDescriptions(@PathVariable("tip") String tip, @PathVariable("disciplina") String disciplina) {
        try {
            Disciplina discip = disciplinaService.getDisciplinaByTitlu(disciplina);
            System.out.println(discip.getId_disciplina());
            List<String> paths = materialeService.getAllDescriptions(discip.getId_disciplina(), tip);
            return new ResponseEntity<>(paths, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/disciplina={disciplina}/tip={tip}/titles", method = {RequestMethod.GET})
    public ResponseEntity<?> getLinkTitle(@PathVariable("tip") String tip, @PathVariable("disciplina") String disciplina) {
        try {
            Disciplina discip = disciplinaService.getDisciplinaByTitlu(disciplina);
            List<String> paths = materialeService.getLinkTitle(discip.getId_disciplina(), tip);
            return new ResponseEntity<>(paths, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/disciplina={disciplina}", method = {RequestMethod.POST})
    public ResponseEntity<?> addLink( @PathVariable("disciplina") String disciplina,@RequestBody Materiale newMateriale) {
        try {
            Disciplina discip = disciplinaService.getDisciplinaByTitlu(disciplina);
            newMateriale.setId_disciplina(discip.getId_disciplina());
            newMateriale.setTip("link");
            System.out.println(newMateriale);
            materialeService.addLink( newMateriale);
            return new ResponseEntity<>("Save successfully saved!", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/disciplina={disciplina}", method = {RequestMethod.GET})
    public ResponseEntity<?> getMateriale( @PathVariable("disciplina") String disciplina) {
        try {
            Disciplina discip = disciplinaService.getDisciplinaByTitlu(disciplina);
            List<Materiale> materiale = materialeService.getSelectedData(discip.getId_disciplina(), "link");
            return new ResponseEntity<>(materiale, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/disciplina={disciplina}/tip={tip}", method = {RequestMethod.GET})
    public ResponseEntity<?> getMateriale(@PathVariable("tip") String tip, @PathVariable("disciplina") String disciplina) {
        try {
            Disciplina discip = disciplinaService.getDisciplinaByTitlu(disciplina);
            List<Materiale> materiale = materialeService.getAllDataSpecific(discip.getId_disciplina(), tip);
            return new ResponseEntity<>(materiale, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/disciplina={disciplina}/tip={tip}", method = {RequestMethod.POST})
    public ResponseEntity<?> addBook( @PathVariable("disciplina") String disciplina, @PathVariable("tip") String tip,@RequestBody Materiale newMateriale) {
        try {
            Disciplina discip = disciplinaService.getDisciplinaByTitlu(disciplina);
            newMateriale.setId_disciplina(discip.getId_disciplina());
            newMateriale.setTip(tip);
            System.out.println(newMateriale);
            materialeService.addLink( newMateriale);
            return new ResponseEntity<>("Save successfully saved!", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
