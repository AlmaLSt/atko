package org.bedu.atko.mapper;

import javax.annotation.processing.Generated;
import org.bedu.atko.dto.Professional.CreateProfessionalDTO;
import org.bedu.atko.dto.Professional.UpdateProfessionalDTO;
import org.bedu.atko.dto.ProfessionalDTO;
import org.bedu.atko.entity.Professional;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-08T21:48:56-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class IProfessionalMapperImpl implements IProfessionalMapper {

    @Override
    public ProfessionalDTO toDTO(Professional data) {
        if ( data == null ) {
            return null;
        }

        ProfessionalDTO.ProfessionalDTOBuilder professionalDTO = ProfessionalDTO.builder();

        professionalDTO.id( data.getId() );
        professionalDTO.name( data.getName() );
        professionalDTO.edad( data.getEdad() );
        professionalDTO.telefono( data.getTelefono() );
        professionalDTO.email( data.getEmail() );
        professionalDTO.areaTrabajo( data.getAreaTrabajo() );
        professionalDTO.categoria( data.getCategoria() );

        return professionalDTO.build();
    }

    @Override
    public Professional toModel(CreateProfessionalDTO data) {
        if ( data == null ) {
            return null;
        }

        Professional professional = new Professional();

        professional.setName( data.getName() );
        professional.setEdad( data.getEdad() );
        professional.setTelefono( data.getTelefono() );
        professional.setEmail( data.getEmail() );
        professional.setAreaTrabajo( data.getAreaTrabajo() );
        professional.setCategoria( data.getCategoria() );

        return professional;
    }

    @Override
    public void toModel(Professional entity, UpdateProfessionalDTO data) {
        if ( data == null ) {
            return;
        }

        if ( data.getName() != null ) {
            entity.setName( data.getName() );
        }
        entity.setEdad( data.getEdad() );
        if ( data.getTelefono() != null ) {
            entity.setTelefono( data.getTelefono() );
        }
        if ( data.getEmail() != null ) {
            entity.setEmail( data.getEmail() );
        }
        if ( data.getAreaTrabajo() != null ) {
            entity.setAreaTrabajo( data.getAreaTrabajo() );
        }
        if ( data.getCategoria() != null ) {
            entity.setCategoria( data.getCategoria() );
        }
    }
}
