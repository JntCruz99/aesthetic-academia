package br.com.aesthetic.aesthetic.academia.controller;

import br.com.aesthetic.aesthetic.academia.domain.model.Nutricionista;
import br.com.aesthetic.aesthetic.academia.service.NutricionistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nutricionistas")
public class NutricionistaController {

    @Autowired
    private NutricionistaService nutricionistaService;

    @GetMapping
    public ResponseEntity<List<Nutricionista>> nutricionistaList(){
        return ResponseEntity.status(HttpStatus.OK).body(nutricionistaService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Nutricionista> nutricionistaFindId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(nutricionistaService.findById(id));
    }
    @PostMapping
    public ResponseEntity<Nutricionista> nutricionistaCreated(@RequestBody Nutricionista nutricionista){
        return ResponseEntity.status(HttpStatus.CREATED).body(nutricionistaService.save(nutricionista));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Nutricionista> nutricionistaPut(@PathVariable Long id, @RequestBody Nutricionista nutricionista){
        return ResponseEntity.status(HttpStatus.OK).body(nutricionistaService.update(nutricionista,id));
    }
}
