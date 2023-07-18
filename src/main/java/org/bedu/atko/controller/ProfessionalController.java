package org.bedu.atko.controller;

import jakarta.validation.Valid;

import org.bedu.atko.dto.professional.CreateProfessionalDTO;
import org.bedu.atko.dto.professional.UpdateProfessionalDTO;
import org.bedu.atko.dto.ProfessionalDTO;
import org.bedu.atko.service.IProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("professionals")
public class ProfessionalController {

    private IProfessionalService service;

    @Autowired
    public ProfessionalController(IProfessionalService service){
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProfessionalDTO> findAll(){
        return service.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProfessionalDTO> findByID(@PathVariable("id") long id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfessionalDTO save(@Valid @RequestBody CreateProfessionalDTO data){
        return service.save(data);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody UpdateProfessionalDTO data, @PathVariable("id") long id){
        service.update(id, data);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id){
        service.delete(id);
    }

}
