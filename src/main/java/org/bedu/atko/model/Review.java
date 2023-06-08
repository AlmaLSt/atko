package org.bedu.atko.model;

public class Review {
    private long id;
    private String descripcion;

    private Professional professional;
    private Client client;
    public Review(long id, String descripcion, Professional professional, Client client) {
        this.id = id;
        this.descripcion = descripcion;
        this.professional = professional;
        this.client = client;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Professional getProfessional() {
        return professional;
    }

    public void setProfessional(Professional professional) {
        this.professional = professional;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
