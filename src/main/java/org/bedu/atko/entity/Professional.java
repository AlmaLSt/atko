package org.bedu.atko.entity;


import jakarta.persistence.*;
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

