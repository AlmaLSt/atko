package org.bedu.atko.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "professionals")
public class Professional {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int edad;
    @Column
    private String telefono;
    @Column
    private String email;
    @Column(nullable = false)
    private String areaTrabajo;
    @Column(nullable = false)
    private String categoria;
}
//
//    public Professional(String name, int edad, String telefono, String email, String areaTrabajo, String categoria) {
//        this.name = name;
//        this.edad = edad;
//        this.telefono = telefono;
//        this.email = email;
//        this.areaTrabajo = areaTrabajo;
//        this.categoria = categoria;
//
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getEdad() {
//        return edad;
//    }
//
//    public void setEdad(int edad) {
//        this.edad = edad;
//    }
//
//    public String getTelefono() {
//        return telefono;
//    }
//
//    public void setTelefono(String telefono) {
//        this.telefono = telefono;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getAreaTrabajo() {
//        return areaTrabajo;
//    }
//
//    public void setAreaTrabajo(String areaTrabajo) {
//        this.areaTrabajo = areaTrabajo;
//    }
//
//    public String getCategoria() {
//        return categoria;
//    }
//
//    public void setCategoria(String categoria) {
//        this.categoria = categoria;
//    }
//}
