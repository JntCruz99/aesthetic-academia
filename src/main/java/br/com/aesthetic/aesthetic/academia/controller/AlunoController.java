package br.com.aesthetic.aesthetic.academia.controller;

import br.com.aesthetic.aesthetic.academia.domain.model.Aluno;
import br.com.aesthetic.aesthetic.academia.domain.repository.AlunoRepository;
import br.com.aesthetic.aesthetic.academia.service.AlunoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;


    @GetMapping
    public ResponseEntity<List<Aluno>> alunoList(){
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> alunoFindId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.findById(id));
    }
    @PostMapping
    public ResponseEntity<Aluno> alunoCreated(@RequestBody Aluno aluno){
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.save(aluno));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> alunoPut(@PathVariable Long id, @RequestBody Aluno aluno){
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.update(aluno,id));
    }

}