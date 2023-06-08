package org.bedu.atko.model;

import java.util.List;

public class Client {
    private long id;
    private String name;
    private int edad;
    private String telefono;
    private String email;

    //Many to many clients-professionals
    private List<Professional> professionals;

    //Opiniones one to many
    private List<Review> reviews;

    public Client(long id, String name, int edad, String telefono, String email, List<Professional> professionals, List<Review> reviews) {
        this.id = id;
        this.name = name;
        this.edad = edad;
        this.telefono = telefono;
        this.email = email;
        this.professionals = professionals;
        this.reviews = reviews;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Professional> getProfessionals() {
        return professionals;
    }

    public void setProfessionals(List<Professional> professionals) {
        this.professionals = professionals;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
