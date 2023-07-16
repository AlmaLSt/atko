package org.bedu.atko.controller;

import jakarta.validation.Valid;
import org.bedu.atko.dto.client.CreateClientDTO;
import org.bedu.atko.dto.client.UpdateClientDTO;
import org.bedu.atko.dto.ClientDTO;
import org.bedu.atko.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("clients")
public class ClientController {

    private final IClientService service;

    @Autowired
    public ClientController(IClientService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> findAll(){
        return service.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ClientDTO> findByID(@PathVariable("id") long id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO save(@Valid @RequestBody CreateClientDTO data){
        return service.save(data);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody UpdateClientDTO data, @PathVariable("id") long id){
        service.update(id, data);
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id){
        service.delete(id);
    }

}
