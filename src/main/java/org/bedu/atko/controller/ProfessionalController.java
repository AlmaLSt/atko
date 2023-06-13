package org.bedu.atko.controller;

import jakarta.validation.Valid;

import org.bedu.atko.model.Professional;
import org.bedu.atko.model.UpdatedProfessional;
import org.bedu.atko.service.ProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("professionals")
public class ProfessionalController {

    private final ProfessionalService lista;

    @Autowired
    public ProfessionalController(ProfessionalService lista) {
        this.lista = lista;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Professional> getProfessionals(){
        return lista.getAll();
    }

    @GetMapping("{name}")
    public Professional getProfessionalByName(@PathVariable("name")String name){
        return lista.getByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Professional createProfessional(@Valid @RequestBody Professional professional){
        return lista.add(professional);
    }

    @PutMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProfessional(@Valid @RequestBody UpdatedProfessional professional, @PathVariable("name")String name){
        lista.update(name, professional);
    }

    @DeleteMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProfessional(@PathVariable("name") String name){
        lista.delete(name);
    }


}
