package org.bedu.atko.mapper;

import org.bedu.atko.dto.professional.CreateProfessionalDTO;
import org.bedu.atko.dto.professional.UpdateProfessionalDTO;
import org.bedu.atko.dto.ProfessionalDTO;
import org.bedu.atko.entity.Professional;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface IProfessionalMapper {

    ProfessionalDTO toDTO(Professional data);

    @Mapping(target = "id", ignore = true)
    Professional toModel(CreateProfessionalDTO data);

    @Mapping(target = "id", ignore = true)
    void toModel(@MappingTarget Professional entity, UpdateProfessionalDTO data);

}
