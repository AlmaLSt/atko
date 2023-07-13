package org.bedu.atko.repository;


import org.bedu.atko.entity.Category;
import org.bedu.atko.entity.Professional;
import org.junit.jupiter.api.*;
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
class IProfessionalRepositoryTest {

    @Autowired
    private IProfessionalRepository professionalRepository;

    Professional professional;

    @BeforeAll
    void setUpDatabase(){
//        professionalRepository.delete();

        Category category = new Category();
        category.setId(1);


        professional = new Professional();
        professional.setName("Camila");
        professional.setEdad(26);
        professional.setTelefono("123456789");
        professional.setEmail("cami@gmail.com");
        professional.setAreaTrabajo("CDMX");
        professional.setCategory(category);

    }

    @AfterAll
    void cleanUp(){
        professionalRepository.deleteAll();
    }

    @Test
    @DisplayName("Should get all professionals")
    void findAll(){
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
        assertNotNull(professional.getId());
    }

    @Test
    @DisplayName("Should update a professional")
    void update(){
//        Category category2 = new Category();
//        category2.setId(2);
       professionalRepository.save(professional);
      Professional updatedProfessional = professionalRepository.findById(professional.getId()).get();
        updatedProfessional.setName("Emilia");
        updatedProfessional.setEdad(23);
        updatedProfessional.setTelefono("112345678");
        updatedProfessional.setEmail("em@gmail.com");
        updatedProfessional.setAreaTrabajo("Taxqueña");
//        updatedProfessional.setCategory(category2);

       updatedProfessional = professionalRepository.save(updatedProfessional);

        assertEquals(updatedProfessional.getName(), "Emilia");
        assertEquals(updatedProfessional.getEdad(), 23);
        assertEquals(updatedProfessional.getTelefono(), "112345678");
        assertEquals(updatedProfessional.getEmail(), "em@gmail.com");
        assertEquals(updatedProfessional.getAreaTrabajo(), "Taxqueña");
//        assertEquals(updatedProfessional.getCategory(), category2);
    }

    @Test
    @DisplayName("Should delete a professional")
    void delete(){

        List<Professional> professionals = professionalRepository.findAll();
        int size = professionals.size();

        professionalRepository.delete(professional);

        professionals = professionalRepository.findAll();
        assertEquals(professionalRepository.findAll().size(), professionals.size());
    }

}