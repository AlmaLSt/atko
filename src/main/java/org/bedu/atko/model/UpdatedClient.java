package org.bedu.atko.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.bedu.atko.service.PhoneNumber;

public class UpdatedClient {

    private int edad;
    @PhoneNumber
    private String telefono;
    @Email
    private String email;


    public UpdatedClient(int edad, String telefono, String email) {
        this.edad = edad;
        this.telefono = telefono;
        this.email = email;
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
