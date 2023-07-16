package org.bedu.atko.repository;


import org.bedu.atko.entity.Category;
import org.bedu.atko.entity.Client;
import org.bedu.atko.entity.Professional;
import org.bedu.atko.entity.Reviews;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ComponentScan(basePackages = "org.bedu.atko")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IProfessionalRepositoryTest {

    @Autowired
    private IProfessionalRepository professionalRepository;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private IClientRepository clientRepository;
    @Autowired
    private IReviewRepository reviewRepository;


    Client client;
    Client client2;
    Professional professional;
    Professional professional2;

    Category category;
    Category category2;

    @BeforeAll
    void setUpDatabase(){
        reviewRepository.deleteAll();
        professionalRepository.deleteAll();
        categoryRepository.deleteAll();
        clientRepository.deleteAll();

        category = new Category();
        category.setName("construcción");
        categoryRepository.save(category);

        category2 = new Category();
        category2.setName("fontanería");
        categoryRepository.save(category2);

        professional = new Professional();
        professional.setName("prueba");
        professional.setEdad(30);
        professional.setTelefono("123-456-7890");
        professional.setEmail("a@a.com");
        professional.setAreaTrabajo("plomero");
        professional.setCategory(category);
        professionalRepository.save(professional);

        professional2 = new Professional();
        professional2.setName("prueba2");
        professional2.setEdad(24);
        professional2.setTelefono("123-456-7890");
        professional2.setEmail("a@a.com");
        professional2.setAreaTrabajo("plomero");
        professional2.setCategory(category2);
        professionalRepository.save(professional2);

        Set<Professional> pro = new HashSet<>();

        pro.add(professional);
        pro.add(professional2);

        client = new Client();
        client.setName("Prueba de cliente");
        client.setEdad(24);
        client.setTelefono("123-456-7890");
        client.setEmail("pruebacliente@prueba.p");
        client.setHired(pro);
        clientRepository.save(client);

        client2 = new Client();
        client2.setName("Prueba de cliente2");
        client2.setEdad(30);
        client2.setTelefono("123-456-7890");
        client2.setEmail("pruebacliente@prueba.p");
        client2.setHired(pro);
        clientRepository.save(client2);



    }

    @AfterAll
    void cleanUp(){
        reviewRepository.deleteAll();
        professionalRepository.deleteAll();
        categoryRepository.deleteAll();
        clientRepository.deleteAll();
    }

    @Test
    @DisplayName("Should get all professionals")
    void findAll(){
        professionalRepository.save(professional);
        professionalRepository.save(professional2);
        List<Professional> professionals = professionalRepository.findAll();
        assertEquals(professionalRepository.findAll().size(), professionals.size());
    }

    @Test
    @DisplayName("Should get a professinal by id")
    void findById(){
        professional = professionalRepository.save(professional);
        Optional<Professional> findProfessional = professionalRepository.findById(professional.getId());
        assertEquals(professional.getId(), findProfessional.get().getId());
    }

    @Test
    @DisplayName("Should save a new professional")
    void save(){
        professional = professionalRepository.save(professional);
        assertNotNull((Long) professional.getId());
    }


    @Test
    @DisplayName("Should delete a professional")
    void delete(){
        professionalRepository.save(professional);
        professionalRepository.save(professional2);
        List<Professional> professionals = professionalRepository.findAll();
        int size = professionals.size();

        professionalRepository.delete(professional);

        professionals = professionalRepository.findAll();
        assertEquals(size-1, professionals.size());
    }

}