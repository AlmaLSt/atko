package org.bedu.atko.mapper;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.bedu.atko.dto.ProfessionalDTO;
import org.bedu.atko.dto.professional.CreateProfessionalDTO;
import org.bedu.atko.dto.professional.UpdateProfessionalDTO;
import org.bedu.atko.entity.Client;
import org.bedu.atko.entity.Professional;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-16T14:15:35-0500",
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
        professionalDTO.category( data.getCategory() );
        Set<Client> set = data.getEmploymentContracts();
        if ( set != null ) {
            professionalDTO.employmentContracts( new LinkedHashSet<Client>( set ) );
        }

        return professionalDTO.build();
    }

    @Override
    public Professional toModel(CreateProfessionalDTO data) {
        if ( data == null ) {
            return null;
        }

        Professional.ProfessionalBuilder professional = Professional.builder();

        professional.name( data.getName() );
        professional.edad( data.getEdad() );
        professional.telefono( data.getTelefono() );
        professional.email( data.getEmail() );
        professional.areaTrabajo( data.getAreaTrabajo() );
        professional.category( data.getCategory() );
        Set<Client> set = data.getEmploymentContracts();
        if ( set != null ) {
            professional.employmentContracts( new LinkedHashSet<Client>( set ) );
        }

        return professional.build();
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
        if ( data.getCategory() != null ) {
            entity.setCategory( data.getCategory() );
        }
        if ( entity.getEmploymentContracts() != null ) {
            Set<Client> set = data.getEmploymentContracts();
            if ( set != null ) {
                entity.getEmploymentContracts().clear();
                entity.getEmploymentContracts().addAll( set );
            }
        }
        else {
            Set<Client> set = data.getEmploymentContracts();
            if ( set != null ) {
                entity.setEmploymentContracts( new LinkedHashSet<Client>( set ) );
            }
        }
    }
}
