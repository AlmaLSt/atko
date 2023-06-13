package org.bedu.atko.service;


import org.bedu.atko.exception.ProfessionalAlreadyExistsException;
import org.bedu.atko.exception.ProfessionalNotFoundException;
import org.bedu.atko.model.Professional;
import org.bedu.atko.model.UpdatedProfessional;
import org.springframework.stereotype.Service;


import java.util.*;


@Service
public class ProfessionalService {
private Map<String, Professional> lista;

public ProfessionalService(){
    lista = new HashMap<String, Professional>();

    lista.put("Enrique", new Professional("Enrique", 20, "7121683645", "enrique@gmail.com", "Atlacomulco", "Carpinteria"));
    lista.put("Tania", new Professional("Tania", 26, "7121688945", "tania@gmail.com", "Cuernavaca", "Reposteria"));
}

public boolean exists(String name){
    return lista.containsKey(name);
    }

    public List<Professional> getAll(){
    return new LinkedList<Professional>(lista.values());
    }

    public Professional getByName(String name){
    if (!exists(name)){
        throw new ProfessionalNotFoundException(name);
        }
        return lista.get(name);
    }

    public Professional add(Professional professional){
    if(exists(professional.getName())){
        throw new ProfessionalAlreadyExistsException();
    }
    lista.put(professional.getName(),professional);
    return professional;
    }

    public void update(String name, UpdatedProfessional professional){
    if(!exists(name)){
        throw new ProfessionalNotFoundException(name);
    }

    Professional current = lista.get(name);
    current.setEdad(professional.getEdad());
    current.setTelefono(professional.getTelefono());
    current.setEmail(professional.getEmail());
    current.setAreaTrabajo(professional.getAreaTrabajo());
    current.setCategoria(professional.getCategoria());
    }
    public void delete(String name){
    if(!exists(name)){
        throw new ProfessionalNotFoundException(name);
        }
    lista.remove(name);
    }
}
