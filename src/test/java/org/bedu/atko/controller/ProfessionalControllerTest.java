package org.bedu.atko.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bedu.atko.dto.professional.CreateProfessionalDTO;
import org.bedu.atko.dto.professional.UpdateProfessionalDTO;
import org.bedu.atko.dto.ProfessionalDTO;
import org.bedu.atko.entity.Category;
import org.bedu.atko.entity.Client;
import org.bedu.atko.entity.Professional;
import org.bedu.atko.service.IProfessionalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureRestDocs
@WebMvcTest(ProfessionalController.class)
class ProfessionalControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProfessionalService professionalService;

    @Test
    @DisplayName("obtiene todos los profesionales")
    void findAll() throws Exception {

        Category category = new Category();
        category.setName("construcción");

        Category category2 = new Category();
        category2.setName("fontanería");

        Professional professional = new Professional();
        professional.setId(1);
        professional.setName("prueba");
        professional.setEdad(30);
        professional.setTelefono("123-456-7890");
        professional.setEmail("a@a.com");
        professional.setAreaTrabajo("plomero");
        professional.setCategory(category);
        Professional professional2 = new Professional();
        professional2.setId(2);
        professional2.setName("prueba2");
        professional2.setEdad(24);
        professional2.setTelefono("123-456-7890");
        professional2.setEmail("a@a.com");
        professional2.setAreaTrabajo("plomero");
        professional2.setCategory(category2);

        Set<Professional> pro = new HashSet<>();
        pro.add(professional);
        pro.add(professional2);

        Client client = new Client();
        client.setId(1);
        client.setName("Prueba de cliente");
        client.setEdad(24);
        client.setTelefono("123-456-7890");
        client.setEmail("pruebacliente@prueba.p");
        client.setHired(pro);
        Client client2 = new Client();
        client2.setId(2);
        client2.setName("Prueba de cliente2");
        client2.setEdad(30);
        client2.setTelefono("123-456-7890");
        client2.setEmail("pruebacliente@prueba.p");
        client2.setHired(pro);

        Set<Client> clientSet = new HashSet<>();
        clientSet.add(client);
        clientSet.add(client2);

        List<ProfessionalDTO> professionals = Arrays.asList(
                ProfessionalDTO.builder().id(1L).name("Nombre").edad(20).telefono("123-456-7890").email("a@a.com").areaTrabajo("plomero").category(category).employmentContracts(clientSet).build(),
                ProfessionalDTO.builder().id(2L).name("Nombre2").edad(21).telefono("123-456-7890").email("a@a.com").areaTrabajo("plomero").category(category).employmentContracts(clientSet).build(),
                ProfessionalDTO.builder().id(3L).name("Nombre3").edad(22).telefono("123-456-7890").email("a@a.com").areaTrabajo("plomero").category(category).employmentContracts(clientSet).build()
        );

        given(professionalService.findAll()).willReturn(professionals);

        mockMvc.perform(get("/api/v1/professionals")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[0].name", is("Nombre")))
                .andExpect(jsonPath("$[1].edad", is(21)))

                .andDo(document("professionals/findAll",
                        responseFields(
                                fieldWithPath("[].id").description("identificador del profesional"),
                                fieldWithPath("[].name").description("nombre del profesional"),
                                fieldWithPath("[].edad").description("edad del profesional"),
                                fieldWithPath("[].telefono").description("telefono del profesional"),
                                fieldWithPath("[].email").description("correo electronico del profesional"),
                                fieldWithPath("[].areaTrabajo").description("area de trabajo del profesional"),
                                subsectionWithPath("[].category").description("categoria del professional"),
                                subsectionWithPath("[].employmentContracts").description("contratos del profesional con el cliente")
                        )));
    }

    @Test
    @DisplayName("obtiene un profesional por su id")
    void findByID() throws Exception {

        Category category = new Category();
        category.setId(1);
        category.setName("construcción");

        Professional professional = new Professional();
        professional.setId(1);
        professional.setName("prueba");
        professional.setEdad(30);
        professional.setTelefono("123-456-7890");
        professional.setEmail("a@a.com");
        professional.setAreaTrabajo("plomero");
        professional.setCategory(category);

        Set<Professional> pro = new HashSet<>();
        pro.add(professional);

        Client client = new Client();
        client.setId(1);
        client.setName("Prueba de cliente");
        client.setEdad(24);
        client.setTelefono("123-456-7890");
        client.setEmail("pruebacliente@prueba.p");
        client.setHired(pro);

        Set<Client> clientSet = new HashSet<>();
        clientSet.add(client);


        given(professionalService.findById(anyLong())).willReturn(Optional.of(ProfessionalDTO.builder().id(1L).name("Nombre").edad(20).telefono("123-456-7890").email("a@a.com").areaTrabajo("plomero").category(category).employmentContracts(clientSet).build()));

        mockMvc.perform(get("/api/v1/professionals/{id}", 1)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Nombre")))
                .andExpect(jsonPath("$.edad", is(20)))
                .andExpect(jsonPath("$.telefono", is("123-456-7890")))
                .andExpect(jsonPath("$.email", is("a@a.com")))
                .andExpect(jsonPath("$.areaTrabajo", is("plomero")))
                .andExpect(jsonPath("$.category.id", is(1)))


                .andDo(document("professionals/findByID",
                        pathParameters(
                                parameterWithName("id").description("Identificador del profesional")
                        ),
                        responseFields(
                                fieldWithPath("id").description("identificador del profesional"),
                                fieldWithPath("name").description("nombre del profesional"),
                                fieldWithPath("edad").description("edad del profesional"),
                                fieldWithPath("telefono").description("telefono del profesional"),
                                fieldWithPath("email").description("correo electronico del profesional"),
                                fieldWithPath("areaTrabajo").description("area de trabajo del profesional"),
                                subsectionWithPath("category").description("categoria del professional"),
                                subsectionWithPath("employmentContracts").description("contratos del profesional con el cliente")
                        )));
    }

    @Test
    @DisplayName("guarda un profesional")
    void save() throws Exception {

        Category category = new Category();
        category.setName("construcción");

        Professional professional = new Professional();
        professional.setId(1);
        professional.setName("prueba");
        professional.setEdad(30);
        professional.setTelefono("123-456-7890");
        professional.setEmail("a@a.com");
        professional.setAreaTrabajo("plomero");
        professional.setCategory(category);

        Set<Professional> pro = new HashSet<>();
        pro.add(professional);

        Client client = new Client();
        client.setId(1);
        client.setName("Prueba de cliente");
        client.setEdad(24);
        client.setTelefono("123-456-7890");
        client.setEmail("pruebacliente@prueba.p");
        client.setHired(pro);

        Set<Client> clientSet = new HashSet<>();
        clientSet.add(client);


        CreateProfessionalDTO profesionalParametro = CreateProfessionalDTO.builder().name("Nombre").edad(20).telefono("123-456-7890").email("a@a.com").areaTrabajo("plomero").category(category).employmentContracts(clientSet).build();
        ProfessionalDTO profesionalRespuesta = ProfessionalDTO.builder().id(1L).name("Nombre").edad(20).telefono("123-456-7890").email("a@a.com").areaTrabajo("plomero").category(category).employmentContracts(clientSet).build();

        given(professionalService.save(profesionalParametro)).willReturn(profesionalRespuesta);

        mockMvc.perform(post("/api/v1/professionals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(profesionalParametro)))
                .andExpect(status().isCreated())

                .andDo(document("professionals/save",
                        requestFields(
                                fieldWithPath("name").description("nombre del profesional"),
                                fieldWithPath("edad").description("edad del profesional"),
                                fieldWithPath("telefono").description("telefono del profesional"),
                                fieldWithPath("email").description("correo electronico del profesional"),
                                fieldWithPath("areaTrabajo").description("area de trabajo del profesional"),
                                subsectionWithPath("category").description("categoria del professional"),
                                subsectionWithPath("employmentContracts").description("contratos del profesional con el cliente")
                        ))
                );
    }

    @Test
    @DisplayName("actualiza un profesional")
    void update() throws Exception {

        Category category = new Category();
        category.setName("construcción");

        Professional professional = new Professional();
        professional.setId(1);
        professional.setName("prueba");
        professional.setEdad(30);
        professional.setTelefono("123-456-7890");
        professional.setEmail("a@a.com");
        professional.setAreaTrabajo("plomero");
        professional.setCategory(category);

        Set<Professional> pro = new HashSet<>();
        pro.add(professional);

        Client client = new Client();
        client.setId(1);
        client.setName("Prueba de cliente");
        client.setEdad(24);
        client.setTelefono("123-456-7890");
        client.setEmail("pruebacliente@prueba.p");
        client.setHired(pro);

        Set<Client> clientSet = new HashSet<>();
        clientSet.add(client);


        UpdateProfessionalDTO profesionalParametro = UpdateProfessionalDTO.builder().name("Nombre").edad(20).telefono("123-456-7890").email("a@a.com").areaTrabajo("plomero").category(category).employmentContracts(clientSet).build();

        mockMvc.perform(put("/api/v1/professionals/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(profesionalParametro)))
                .andExpect(status().isNoContent())

                .andDo(document("professionals/update",
                        pathParameters(
                                parameterWithName("id").description("Identificador del profesional")
                        ),
                        requestFields(
                                fieldWithPath("name").description("nombre del profesional"),
                                fieldWithPath("edad").description("edad del profesional"),
                                fieldWithPath("telefono").description("telefono del profesional"),
                                fieldWithPath("email").description("correo electronico del profesional"),
                                fieldWithPath("areaTrabajo").description("area de trabajo del profesional"),
                                subsectionWithPath("category").description("categoria del professional"),
                                subsectionWithPath("employmentContracts").description("contratos del profesional con el cliente")
                        )
                ));
    }

    @Test
    @DisplayName("elimina un profesional")
    void deleteReview() throws Exception {
        mockMvc.perform(delete("/api/v1/professionals/{id}", 1)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())

                .andDo(document("professionals/delete-professional",
                        pathParameters(
                                parameterWithName("id").description("Identificador del profesional")
                        )));
    }
}
