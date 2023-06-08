package org.bedu.atko.model;

import java.util.ArrayList;
import java.util.List;

public class Professional {

    private long id;
    private String name;
    private int edad;
    private String  telefono;
    private String email;
    private String areaTrabajo;

    //Many-to-many relationship
    private List<Category> categories;

    //Many to many professional-clients
    private List<Client> clients;

    //Opiniones one to many
    private List<Review> reviews;

    public Professional(long id, String name, int edad, String telefono, String email, String areaTrabajo, List<Category> categories, List<Client> clients, List<Review> reviews) {
        this.id = id;
        this.name = name;
        this.edad = edad;
        this.telefono = telefono;
        this.email = email;
        this.areaTrabajo = areaTrabajo;
        this.categories = categories;
        this.clients = clients;
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

    public String getAreaTrabajo() {
        return areaTrabajo;
    }

    public void setAreaTrabajo(String areaTrabajo) {
        this.areaTrabajo = areaTrabajo;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }


    public void addCategory(Category category){
        if(categories ==null){
            categories = new ArrayList<>();
        } else if (categories.contains(category)){
            return;
        }
        categories.add(category);
    }

    public void removeCategory(Category category){
        if(categories !=null && categories.contains(category)){
            categories.remove(category);
        }
    }

}
