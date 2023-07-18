package org.bedu.atko.dto;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CategoryDTO {
    private long id;
    private String name;
}
