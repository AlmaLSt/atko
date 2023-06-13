package org.bedu.atko.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.bedu.atko.service.PhoneNumber;

public class Professional {
@NotBlank
    private String name;
    private int edad;
    @PhoneNumber
    private String  telefono;
    @Email
    private String email;
    @NotBlank
    private String areaTrabajo;
@NotBlank
    private String categoria;

    public Professional(String name, int edad, String telefono, String email, String areaTrabajo, String categoria) {
        this.name = name;
        this.edad = edad;
        this.telefono = telefono;
        this.email = email;
        this.areaTrabajo = areaTrabajo;
        this.categoria = categoria;

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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
