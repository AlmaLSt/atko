package org.bedu.atko.dto.Client;

import lombok.Data;

@Data
public class UpdateClientDTO {
    private String name;
    private Integer edad;
    private String telefono;
    private String email;
}
