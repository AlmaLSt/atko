package org.bedu.atko.repository;

import org.bedu.atko.entity.Client;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ComponentScan(basePackages = "org.bedu.atko")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IClientRepositoryTest {
    @Autowired
    private IClientRepository clientRepository;
    @Autowired
    private IProfessionalRepository professionalRepository;
    @Autowired
    private IReviewRepository reviewRepository;

    Client client;
    Client client2;

    @BeforeAll
    void setUpDatebase(){
        reviewRepository.deleteAll();
        clientRepository.deleteAll();
        professionalRepository.deleteAll();


        client = new Client();
        client.setName("Prueba de cliente");
        client.setEdad(24);
        client.setTelefono("123-456-7890");
        client.setEmail("pruebacliente@prueba.p");

        client2 = new Client();
        client2.setName("Prueba de cliente2");
        client2.setEdad(30);
        client2.setTelefono("123-456-7890");
        client2.setEmail("pruebacliente@prueba.p");
    }

    @Test
    @DisplayName("Guarda un cliente")
    void save(){
        client = clientRepository.save(client);
        assertNotNull((Long) client.getId());
    }

    @Test
    @DisplayName("Obtiene todos los clientes")
    void findAll() {
        client = clientRepository.save(client);
        client2 = clientRepository.save(client2);
        List<Client> clients = clientRepository.findAll();
        assertEquals(2,clients.size());
    }
    @Test
    @DisplayName("Obtiene un cliente por su id")
    void findById(){
        client = clientRepository.save(client);
        client2 = clientRepository.save(client2);
        Optional<Client> clientBuscar = clientRepository.findById(client2.getId());
        assertEquals(client2.getId(), clientBuscar.get().getId());
    }

    @Test
    @DisplayName("Elimina un cliente")
    void delete(){
        client = clientRepository.save(client);
        client2 = clientRepository.save(client2);
        List<Client> clients = clientRepository.findAll();
        int size = clients.size();
        clientRepository.delete(client);
        clients = clientRepository.findAll();
        assertEquals(size-1, clients.size());
    }

}