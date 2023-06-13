package org.bedu.atko.service;


import org.bedu.atko.exception.ClientAlreadyExistsException;
import org.bedu.atko.exception.ClientNotFoundException;
import org.bedu.atko.model.Client;
import org.bedu.atko.model.UpdatedClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class ClientService {
    private Map<String, Client> listaClientes;

    public ClientService(){
        listaClientes = new HashMap<String, Client>();

        listaClientes.put("Beto", new Client("Beto", 23, "123456789", "beto@gmail.com"));
        listaClientes.put("Gary", new Client("Gary", 45, "7894561230", "gary@gmail.com"));
    }

    public boolean exists(String name){
        return listaClientes.containsKey(name);
    }

    public List<Client> getAll(){
        return new LinkedList<Client>(listaClientes.values());
    }

    public Client getByName(String name){
        if (!exists(name)){
            throw new ClientNotFoundException(name);
        }
        return listaClientes.get(name);
    }

    public Client add(Client client){
        if(exists(client.getName())){
            throw new ClientAlreadyExistsException();
        }
        listaClientes.put(client.getName(),client);
        return client;
    }

    public void update(String name, UpdatedClient client){
        if(!exists(name)){
            throw new ClientNotFoundException(name);
        }

        Client current = listaClientes.get(name);
        current.setEdad(client.getEdad());
        current.setTelefono(client.getTelefono());
        current.setEmail(client.getEmail());;
    }
    public void delete(String name){
        if(!exists(name)){
            throw new ClientNotFoundException(name);
        }
        listaClientes.remove(name);
    }
}
