package org.bedu.atko.service;

import org.assertj.core.api.Assertions;
import org.bedu.atko.dto.client.CreateClientDTO;
import org.bedu.atko.dto.client.UpdateClientDTO;
import org.bedu.atko.dto.ClientDTO;
import org.bedu.atko.entity.Client;
import org.bedu.atko.exception.ClientNotFoundException;
import org.bedu.atko.mapper.IClientMapper;
import org.bedu.atko.repository.IClientRepository;
import org.bedu.atko.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private IClientRepository clientRepository;

    @Mock
    private IClientMapper mapper;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    @DisplayName("Should save a client")
    void saveClient(){
        Client client = Client.builder()
                .name("Ana")
                .edad(27)
                .telefono("123456789")
                .email("ana@gmail.com")
                .build();

        ClientDTO clientDTO = ClientDTO.builder().name("Ana").edad(27).telefono("123456789").email("ana@gmail.com").build();
        CreateClientDTO createClientDTO = CreateClientDTO.builder().name("Ana").edad(27).telefono("123456789").email("ana@gmail.com").build();

        when(clientRepository.save(Mockito.any(Client.class))).thenReturn(client);
        when(mapper.toModel(createClientDTO)).thenReturn(client);
        when(mapper.toDTO(client)).thenReturn(clientDTO);

        ClientDTO savedClient = clientService.save(createClientDTO);

        Assertions.assertThat(savedClient).isNotNull();

        verify(clientRepository, times(1)).save(client);
        verify(mapper, times(1)).toModel(createClientDTO);
        verify(mapper, times(1)).toDTO(client);
    }

    @Test
    @DisplayName("Should find all clients")
    void findAllClients(){
        List<Client> clientList = new ArrayList<>();

        when(clientRepository.findAll()).thenReturn(clientList);

        List<ClientDTO> found = clientService.findAll();

        Assertions.assertThat(found).isNotNull();

        verify(clientRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should find client by id")
    void findClientById(){
        Client client = Client.builder()
                .id(1L)
                .name("Ana")
                .edad(27)
                .telefono("123456789")
                .email("ana@gmail.com")
                .build();

        ClientDTO clientDTO = ClientDTO.builder().name("Ana").edad(27).telefono("123456789").email("ana@gmail.com").build();

        Optional<Client> clientId = Optional.of(client);

        when(clientRepository.findById(client.getId())).thenReturn(clientId);
        when(mapper.toDTO(client)).thenReturn(clientDTO);

        Optional<ClientDTO>  foundId = clientService.findById(1L);

        Assertions.assertThat(foundId).isNotNull();
        verify(clientRepository, times(1)).findById(1L);
        verify(mapper, times(1)).toDTO(client);
    }

    @Test
    @DisplayName("Should update client")
    void updateClient(){
        Client client = Client.builder()
                .id(1L)
                .name("Ana")
                .edad(27)
                .telefono("123456789")
                .email("ana@gmail.com")
                .build();

        UpdateClientDTO updatedClient = UpdateClientDTO.builder().telefono("123456799").build();
        assertThatThrownBy(() -> clientService.update(client.getId(), updatedClient))
                .isInstanceOf(ClientNotFoundException.class);
    }

    @Test
    @DisplayName("Should delete a client")
    void deleteClient(){
        Client client = Client.builder()
                .id(1L)
                .name("Ana")
                .edad(27)
                .telefono("123456789")
                .email("ana@gmail.com")
                .build();

        clientService.delete(client.getId());

        assertAll(() -> clientService.delete(client.getId()));
    }

}