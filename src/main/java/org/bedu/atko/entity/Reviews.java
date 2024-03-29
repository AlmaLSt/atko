package org.bedu.atko.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "reviews")
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "professional_id")
    private Professional professional;
    @ManyToOne
    @JoinColumn(name = "clients_id")
    private Client clients;
    @Column(nullable = false)
    private String description;

}
