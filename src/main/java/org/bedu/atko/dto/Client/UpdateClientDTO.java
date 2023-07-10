package org.bedu.atko.dto.Client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UpdateClientDTO {
    private String name;
    private Integer edad;
    private String telefono;
    private String email;
}
