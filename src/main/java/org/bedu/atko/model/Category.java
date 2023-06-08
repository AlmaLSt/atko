package org.bedu.atko.model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private long id;
    private String categoria;

    //Many-to-many with Professional
    private List<Professional> professionals;

    public Category(long id, String categoria, List<Professional> professionals) {
        this.id = id;
        this.categoria = categoria;
        this.professionals = professionals;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Professional> getProfessionals() {
        return professionals;
    }

    public void setProfessionals(List<Professional> professionals) {
        this.professionals = professionals;
    }

    public void addProfessional(Professional professional){
        if(professionals ==null){
            professionals = new ArrayList<>();
        } else if (professionals.contains(professional)){
            return;
        }
        professionals.add(professional);
    }

    public void removeProfessional(Professional professional){
        if(professionals !=null && professionals.contains(professional)){
            professionals.remove(professional);
        }
    }
}
