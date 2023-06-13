package org.bedu.atko.controller;

import jakarta.validation.Valid;
import org.bedu.atko.model.Client;
import org.bedu.atko.model.Professional;
import org.bedu.atko.model.UpdatedClient;
import org.bedu.atko.model.UpdatedProfessional;
import org.bedu.atko.service.ClientService;
import org.bedu.atko.service.ProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientController {

    private final ClientService listaClientes;

    @Autowired
    public ClientController(ClientService listaClientes) {
        this.listaClientes = listaClientes;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getClients(){
        return listaClientes.getAll();
    }

    @GetMapping("{name}")
    public Client getClientByName(@PathVariable("name")String name){
        return listaClientes.getByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client createClient(@Valid @RequestBody Client client){
        return listaClientes.add(client);
    }

    @PutMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public void updateClient(@Valid @RequestBody UpdatedClient client, @PathVariable("name")String name){
        listaClientes.update(name, client);
    }

    @DeleteMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteClient(@PathVariable("name") String name){
        listaClientes.delete(name);
    }


}
