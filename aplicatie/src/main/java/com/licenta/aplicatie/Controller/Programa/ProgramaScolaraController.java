package com.licenta.aplicatie.Controller.Programa;

import com.licenta.aplicatie.Models.Programa.Disciplina;
import com.licenta.aplicatie.Models.Programa.ProgramaScolara;
import com.licenta.aplicatie.Service.Programa.ProgramaScolaraService;
import com.licenta.aplicatie.Service.Users.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/licenta/programaScolara")
public class ProgramaScolaraController {
    @Autowired
    ProgramaScolaraService programaScolaraService;

    @CrossOrigin
    @RequestMapping(value="/programStudii={programStudii}/an={an}", method = { RequestMethod.GET })
    public ResponseEntity<?> listDisciplineByYear(@PathVariable("programStudii") String programStudii,@PathVariable("an") int an){
        try{
            List<Disciplina> discipline=programaScolaraService.getDisciplineByAn(programStudii,an);
            return new ResponseEntity<>(discipline,HttpStatus.OK);

        }catch (Exception ex)
        {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value="/programStudii={programStudii}/specializare={specializare}", method = { RequestMethod.GET })
    public ResponseEntity<?> listDisciplineBySpecializare(@PathVariable("programStudii") String programStudii,@PathVariable("specializare") String specializare){
        try{
            List<Disciplina> discipline=programaScolaraService.getDisciplineBySpecializare(programStudii,specializare);
            return new ResponseEntity<>(discipline,HttpStatus.OK);

        }catch (Exception ex)
        {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value="/programStudii={programStudii}/specializare={specializare}/an={an}", method = { RequestMethod.GET })
    public ResponseEntity<?> listDisciplineBySpecializareAn(@PathVariable("programStudii") String programStudii,@PathVariable("specializare") String specializare,@PathVariable("an") int an){
        try{
            List<Disciplina> discipline=programaScolaraService.getDisciplinesBySpecializareAndAn(programStudii,specializare,an);
            return new ResponseEntity<>(discipline,HttpStatus.OK);

        }catch (Exception ex)
        {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value="/programStudii={programStudii}/an={an}/semestru={semestru}", method = { RequestMethod.GET })
    public ResponseEntity<?> listDisciplineByAnSemestru(@PathVariable("programStudii") String programStudii,@PathVariable("semestru") int semestru,@PathVariable("an") int an){
        try{
            List<Disciplina> discipline=programaScolaraService.getDisciplinesByAnAndSemestru(programStudii,an,semestru);
            return new ResponseEntity<>(discipline,HttpStatus.OK);

        }catch (Exception ex)
        {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value="/programStudii={programStudii}/specializare={specializare}/an={an}/semestru={semestru}", method = { RequestMethod.GET })
    public ResponseEntity<?> listAll(@PathVariable("programStudii") String programStudii,@PathVariable("specializare") String specializare,@PathVariable("semestru") int semestru,@PathVariable("an") int an){
        try{
            List<Disciplina> discipline=programaScolaraService.getDiscipline(programStudii,specializare,an,semestru);
            return new ResponseEntity<>(discipline,HttpStatus.OK);

        }catch (Exception ex)
        {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
