package org.bedu.atko.dto.professional;

import lombok.*;
import org.bedu.atko.entity.Category;
import org.bedu.atko.entity.Client;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UpdateProfessionalDTO {
    private String name;
    private int edad;
    private String telefono;
    private String email;
    private String areaTrabajo;
    private Category category;

    private Set<Client> employmentContracts;
}
