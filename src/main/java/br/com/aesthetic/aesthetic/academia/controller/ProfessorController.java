package br.com.aesthetic.aesthetic.academia.controller;

import br.com.aesthetic.aesthetic.academia.domain.model.Nutricionista;
import br.com.aesthetic.aesthetic.academia.domain.model.Professor;
import br.com.aesthetic.aesthetic.academia.service.NutricionistaService;
import br.com.aesthetic.aesthetic.academia.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<Professor>> professorList(){
        return ResponseEntity.status(HttpStatus.OK).body(professorService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Professor> professorFindId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(professorService.findById(id));
    }
    @PostMapping
    public ResponseEntity<Professor> professorCreated(@RequestBody Professor professor){
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.save(professor));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Professor> professorPut(@PathVariable Long id, @RequestBody Professor professor){
        return ResponseEntity.status(HttpStatus.OK).body(professorService.update(professor,id));
    }
}
