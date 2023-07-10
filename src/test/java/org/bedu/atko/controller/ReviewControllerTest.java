package org.bedu.atko.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bedu.atko.dto.Review.CreateReviewDTO;
import org.bedu.atko.dto.Review.UpdateReviewDTO;
import org.bedu.atko.dto.ReviewDTO;
import org.bedu.atko.entity.Client;
import org.bedu.atko.entity.Professional;
import org.bedu.atko.service.IReviewServices;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

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
@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IReviewServices reviewServices;

    @Test
    @DisplayName("obtiene todos los reviews")
    void findAll() throws Exception {

        Client client = new Client();
        client.setId(1);
        client.setName("Prueba de cliente");
        client.setEdad(24);
        client.setTelefono("123-456-7890");
        client.setEmail("pruebacliente@prueba.p");
        Client client2 = new Client();
        client2.setId(2);
        client2.setName("Prueba de cliente2");
        client2.setEdad(30);
        client2.setTelefono("123-456-7890");
        client2.setEmail("pruebacliente@prueba.p");

        Professional professional = new Professional();
        professional.setId(1);
        professional.setName("prueba");
        professional.setEdad(30);
        professional.setTelefono("123-456-7890");
        professional.setEmail("a@a.com");
        professional.setAreaTrabajo("plomero");
        professional.setCategoria("plomero");
        Professional professional2 = new Professional();
        professional2.setId(2);
        professional2.setName("prueba2");
        professional2.setEdad(24);
        professional2.setTelefono("123-456-7890");
        professional2.setEmail("a@a.com");
        professional2.setAreaTrabajo("plomero");
        professional2.setCategoria("plomero");

        List<ReviewDTO> reviews = Arrays.asList(
                ReviewDTO.builder().id(1L).professional(professional).clients(client).description("prueba review1").build(),
                ReviewDTO.builder().id(2L).professional(professional2).clients(client2).description("prueba review2").build(),
                ReviewDTO.builder().id(3L).professional(professional).clients(client2).description("prueba review3").build()
        );

        given(reviewServices.getAll()).willReturn(reviews);

        mockMvc.perform(get("/api/v1/reviews")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[0].description", is("prueba review1")))
                .andExpect(jsonPath("$[2].description", is("prueba review3")))

                .andDo(document("reviews/findAll",
                        responseFields(
                                fieldWithPath("[].id").description("identificador del review"),
                                subsectionWithPath("[].professional").description("datos del professional"),
                                subsectionWithPath("[].clients").description("datos del cliente"),
                                fieldWithPath("[].description").description("descripción del review")

                        )));
    }

    @Test
    @DisplayName("obtiene un review por el id del profesional")
    void findByProfessional() throws Exception {

        Client client = new Client();
        client.setId(1);
        client.setName("Prueba de cliente");
        client.setEdad(24);
        client.setTelefono("123-456-7890");
        client.setEmail("pruebacliente@prueba.p");
        Client client2 = new Client();
        client2.setId(2);
        client2.setName("Prueba de cliente2");
        client2.setEdad(30);
        client2.setTelefono("123-456-7890");
        client2.setEmail("pruebacliente@prueba.p");

        Professional professional = new Professional();
        professional.setId(1);
        professional.setName("prueba");
        professional.setEdad(30);
        professional.setTelefono("123-456-7890");
        professional.setEmail("a@a.com");
        professional.setAreaTrabajo("plomero");
        professional.setCategoria("plomero");
        Professional professional2 = new Professional();
        professional2.setId(2);
        professional2.setName("prueba2");
        professional2.setEdad(24);
        professional2.setTelefono("123-456-7890");
        professional2.setEmail("a@a.com");
        professional2.setAreaTrabajo("plomero");
        professional2.setCategoria("plomero");

        List<ReviewDTO> reviews = Arrays.asList(
                ReviewDTO.builder().id(1L).professional(professional).clients(client).description("prueba review1").build(),
                ReviewDTO.builder().id(2L).professional(professional2).clients(client2).description("prueba review2").build(),
                ReviewDTO.builder().id(3L).professional(professional).clients(client2).description("prueba review3").build()
        );
        given(reviewServices.getByProfessional(anyLong())).willReturn(reviews);

        mockMvc.perform(get("/api/v1/reviews/{professional_id}", 1)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[0].description", is("prueba review1")))
                .andExpect(jsonPath("$[2].description", is("prueba review3")))

                .andDo(document("reviews/findByProfessional",
                        responseFields(
                                fieldWithPath("[].id").description("identificador del review"),
                                subsectionWithPath("[].professional").description("datos del professional"),
                                subsectionWithPath("[].clients").description("datos del cliente"),
                                fieldWithPath("[].description").description("descripción del review")

                        )));
    }

    @Test
    @DisplayName("guarda un review")
    void save() throws Exception {

        Client client = new Client();
        client.setId(1);
        client.setName("Prueba de cliente");
        client.setEdad(24);
        client.setTelefono("123-456-7890");
        client.setEmail("pruebacliente@prueba.p");

        Professional professional = new Professional();
        professional.setId(1);
        professional.setName("prueba");
        professional.setEdad(30);
        professional.setTelefono("123-456-7890");
        professional.setEmail("a@a.com");
        professional.setAreaTrabajo("plomero");
        professional.setCategoria("plomero");

        CreateReviewDTO reviewParametro = CreateReviewDTO.builder().professional(professional).clients(client).description("prueba review1").build();
        ReviewDTO reviewRespuesta = ReviewDTO.builder().id(1L).professional(professional).clients(client).description("prueba review1").build();

        given(reviewServices.save(reviewParametro)).willReturn(reviewRespuesta);

        mockMvc.perform(post("/api/v1/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(reviewParametro)))
                .andExpect(status().isCreated())

                .andDo(document("reviews/save",
                        requestFields(
                                subsectionWithPath("professional").description("datos del profesional"),
                                subsectionWithPath("clients").description("datos del cliente"),
                                fieldWithPath("description").description("descripción del review")
                        ))
                );
    }

    @Test
    @DisplayName("actualiza un review")
    void update() throws Exception {

        Client client = new Client();
        client.setId(1);
        client.setName("Prueba de cliente");
        client.setEdad(24);
        client.setTelefono("123-456-7890");
        client.setEmail("pruebacliente@prueba.p");

        Professional professional = new Professional();
        professional.setId(1);
        professional.setName("prueba");
        professional.setEdad(30);
        professional.setTelefono("123-456-7890");
        professional.setEmail("a@a.com");
        professional.setAreaTrabajo("plomero");
        professional.setCategoria("plomero");


        UpdateReviewDTO reviewParametro = UpdateReviewDTO.builder().professional(professional).clients(client).description("prueba review1").build();

        mockMvc.perform(put("/api/v1/reviews/{review_id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(reviewParametro)))
                .andExpect(status().isNoContent())

                .andDo(document("reviews/update",
                        pathParameters(
                                parameterWithName("review_id").description("Identificador del review")
                        ),
                        requestFields(
                                subsectionWithPath("professional").description("datos del profesional"),
                                subsectionWithPath("clients").description("datos del cliente"),
                                fieldWithPath("description").description("descripción del review")
                        )
                ));
    }

    @Test
    @DisplayName("elimina un review")
    void deleteReview() throws Exception {
        mockMvc.perform(delete("/api/v1/reviews/{review_id}", 1)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())

                .andDo(document("reviews/delete-review",
                        pathParameters(
                                parameterWithName("review_id").description("Identificador del review")
                        )));
    }
}