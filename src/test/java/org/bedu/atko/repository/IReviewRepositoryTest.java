package org.bedu.atko.repository;

import org.bedu.atko.entity.Category;
import org.bedu.atko.entity.Client;
import org.bedu.atko.entity.Professional;
import org.bedu.atko.entity.Reviews;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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
class IReviewRepositoryTest {

    @Autowired
    private IClientRepository clientRepository;
    @Autowired
    private IProfessionalRepository professionalRepository;
    @Autowired
    private IReviewRepository reviewRepository;
    @Autowired
    private ICategoryRepository categoryRepository;

    Reviews reviews;
    Reviews reviews2;
    Client client;
    Client client2;
    Professional professional;
    Professional professional2;

    Category category;
    Category category2;

    @BeforeAll
    void setUpDatebase(){
        reviewRepository.deleteAll();
        categoryRepository.deleteAll();
        clientRepository.deleteAll();
        professionalRepository.deleteAll();


        category = new Category();
        category.setName("construcción");
        categoryRepository.save(category);

        category2 = new Category();
        category2.setName("fontanería");
        categoryRepository.save(category2);

        professional = new Professional();
        //professional.setId(6);
        professional.setName("prueba");
        professional.setEdad(30);
        professional.setTelefono("123-456-7890");
        professional.setEmail("a@a.com");
        professional.setAreaTrabajo("plomero");
        professional.setCategory(category);
        professionalRepository.save(professional);
        professional2 = new Professional();
        //professional.setId(7);
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
        //client.setId(14);
        client.setName("Prueba de cliente");
        client.setEdad(24);
        client.setTelefono("123-456-7890");
        client.setEmail("pruebacliente@prueba.p");
        client.setHired(pro);
        clientRepository.save(client);
        client2 = new Client();
        //client2.setId(15);
        client2.setName("Prueba de cliente2");
        client2.setEdad(30);
        client2.setTelefono("123-456-7890");
        client2.setEmail("pruebacliente@prueba.p");
        client2.setHired(pro);
        clientRepository.save(client2);

        reviews = new Reviews();
        reviews.setDescription("Prueba de review1");
        reviews.setClients(client);
        reviews.setProfessional(professional);

        reviews2 = new Reviews();
        reviews2.setDescription("Prueba de review2");
        reviews2.setClients(client2);
        reviews2.setProfessional(professional2);
    }

    @Test
    @DisplayName("Guarda un review")
    void save(){
        reviews = reviewRepository.save(reviews);
        assertNotNull((Long) reviews.getId());
    }

    @Test
    @DisplayName("Obtiene todos los reviews")
    void getAll() {
        reviews = reviewRepository.save(reviews);
        reviews2 = reviewRepository.save(reviews2);
        List<Reviews> reviewsList = reviewRepository.findAll();
        assertEquals(2,reviewsList.size());
    }

    @Test
    @DisplayName("Elimina un review")
    void delete(){
        reviews = reviewRepository.save(reviews);
        reviews2 = reviewRepository.save(reviews2);
        List<Reviews> reviewsList = reviewRepository.findAll();
        int size = reviewsList.size();
        reviewRepository.delete(reviews);
        reviewsList = reviewRepository.findAll();
        assertEquals(size-1, reviewsList.size());
    }

    @Test
    void findByProfessional_id() {
        reviews = reviewRepository.save(reviews);
        reviews2 = reviewRepository.save(reviews2);
        List<Reviews> reviewsList = reviewRepository.findByProfessional_id(professional.getId());
        assertEquals(1,reviewsList.size());
    }
}