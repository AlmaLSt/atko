package org.bedu.atko.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int edad;

    @Column
    private String telefono;

    @Column
    private String email;

    @ManyToMany(mappedBy = "employmentContracts")
    Set<Professional> hired;
}

