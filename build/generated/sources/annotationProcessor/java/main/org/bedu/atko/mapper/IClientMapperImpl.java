package org.bedu.atko.mapper;

import javax.annotation.processing.Generated;
import org.bedu.atko.dto.Client.CreateClientDTO;
import org.bedu.atko.dto.Client.UpdateClientDTO;
import org.bedu.atko.dto.ClientDTO;
import org.bedu.atko.entity.Client;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-08T21:48:56-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class IClientMapperImpl implements IClientMapper {

    @Override
    public ClientDTO toDTO(Client data) {
        if ( data == null ) {
            return null;
        }

        ClientDTO.ClientDTOBuilder clientDTO = ClientDTO.builder();

        clientDTO.id( data.getId() );
        clientDTO.name( data.getName() );
        clientDTO.edad( data.getEdad() );
        clientDTO.telefono( data.getTelefono() );
        clientDTO.email( data.getEmail() );

        return clientDTO.build();
    }

    @Override
    public Client toModel(CreateClientDTO data) {
        if ( data == null ) {
            return null;
        }

        Client client = new Client();

        client.setName( data.getName() );
        client.setEdad( data.getEdad() );
        client.setTelefono( data.getTelefono() );
        client.setEmail( data.getEmail() );

        return client;
    }

    @Override
    public void toModel(Client entity, UpdateClientDTO data) {
        if ( data == null ) {
            return;
        }

        if ( data.getName() != null ) {
            entity.setName( data.getName() );
        }
        if ( data.getEdad() != null ) {
            entity.setEdad( data.getEdad() );
        }
        if ( data.getTelefono() != null ) {
            entity.setTelefono( data.getTelefono() );
        }
        if ( data.getEmail() != null ) {
            entity.setEmail( data.getEmail() );
        }
    }
}
