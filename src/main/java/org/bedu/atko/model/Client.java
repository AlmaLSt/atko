package org.bedu.atko.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.bedu.atko.service.PhoneNumber;

import java.util.List;

public class Client {
    @NotBlank
    private String name;

    private int edad;
    @PhoneNumber
    private String telefono;
    @Email
    private String email;


    public Client(String name, int edad, String telefono, String email) {
        this.name = name;
        this.edad = edad;
        this.telefono = telefono;
        this.email = email;
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

}
