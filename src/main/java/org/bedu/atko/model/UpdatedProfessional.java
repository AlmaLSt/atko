package org.bedu.atko.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.bedu.atko.service.PhoneNumber;

public class UpdatedProfessional {
    @Size(max = 2)
    private int edad;
    @PhoneNumber
    private String  telefono;
    @Email
    private String email;
    @NotBlank
    private String areaTrabajo;
    @NotBlank
    private String categoria;

    public UpdatedProfessional(int edad, String telefono, String email, String areaTrabajo, String categoria) {
        this.edad = edad;
        this.telefono = telefono;
        this.email = email;
        this.areaTrabajo = areaTrabajo;
        this.categoria = categoria;

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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
