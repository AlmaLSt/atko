package org.bedu.atko.dto;

import lombok.*;
import org.bedu.atko.entity.Professional;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ClientDTO {

    private long id;
    private String name;
    private int edad;
    private String telefono;
    private String email;

    private Set<Professional> hired;
}
