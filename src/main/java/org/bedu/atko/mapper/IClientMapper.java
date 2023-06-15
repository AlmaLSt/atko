package org.bedu.atko.mapper;

import org.bedu.atko.dto.Client.CreateClientDTO;
import org.bedu.atko.dto.Client.UpdateClientDTO;
import org.bedu.atko.dto.ClientDTO;
import org.bedu.atko.entity.Client;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface IClientMapper {

    ClientDTO toDTO(Client data);

    @Mapping(target = "id", ignore = true)
    Client toModel(CreateClientDTO data);


    @Mapping(target = "id", ignore = true)
    void toModel(@MappingTarget Client entity, UpdateClientDTO data);
}
