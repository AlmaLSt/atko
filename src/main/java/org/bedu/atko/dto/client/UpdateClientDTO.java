package org.bedu.atko.dto.client;

import lombok.*;
import org.bedu.atko.entity.Professional;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UpdateClientDTO {
    private String name;
    private Integer edad;
    private String telefono;
    private String email;
    private Set<Professional> hired;
}
