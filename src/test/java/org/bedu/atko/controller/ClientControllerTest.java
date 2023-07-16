package org.bedu.atko.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bedu.atko.dto.client.CreateClientDTO;
import org.bedu.atko.dto.client.UpdateClientDTO;
import org.bedu.atko.dto.ClientDTO;
import org.bedu.atko.service.IClientService;
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
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureRestDocs
@WebMvcTest(ClientController.class)
class ClientControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IClientService clientService;

    @Test
    @DisplayName("controlador obtiene un cliente por su id")
    void findByID() throws Exception {
        given(clientService.findById(anyLong())).willReturn(Optional.of(ClientDTO.builder().id(1L).name("Nombre").edad(20).telefono("123-456-7890").email("cliente@contacto.com").build()));

        mockMvc.perform(get("/api/v1/clients/{id}", 1)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Nombre")))
                .andExpect(jsonPath("$.edad", is(20)))
                .andExpect(jsonPath("$.telefono", is("123-456-7890")))
                .andExpect(jsonPath("$.email", is("cliente@contacto.com")))


                .andDo(document("clients/findByID",
                        pathParameters(
                                parameterWithName("id").description("Identificador del cliente")
                        ),
                        responseFields(
                                fieldWithPath("id").description("identificador del cliente"),
                                fieldWithPath("name").description("nombre del cliente"),
                                fieldWithPath("edad").description("edad del cliente"),
                                fieldWithPath("telefono").description("telefono del cliente"),
                                fieldWithPath("email").description("correo electronico del cliente"),
                                subsectionWithPath("hired").description("datos del contrante")
                        )));
    }

    @Test
    @DisplayName("obtiene todos los clientes")
    void findAll() throws Exception {

        List<ClientDTO> clientes = Arrays.asList(
                ClientDTO.builder().id(1L).name("Nombre").edad(20).telefono("123-456-7890").email("cliente@contacto.com").build(),
                ClientDTO.builder().id(2L).name("Nombre2").edad(21).telefono("123-456-7890").email("cliente2@contacto.com").build(),
                ClientDTO.builder().id(3L).name("Nombre3").edad(22).telefono("123-456-7890").email("cliente3@contacto.com").build()
        );

        given(clientService.findAll()).willReturn(clientes);

        mockMvc.perform(get("/api/v1/clients")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[0].email", is("cliente@contacto.com")))
                .andExpect(jsonPath("$[2].name", is("Nombre3")))

                .andDo(document("clients/findAll",
                        responseFields(
                                fieldWithPath("[].id").description("identificador del cliente"),
                                fieldWithPath("[].name").description("nombre del cliente"),
                                fieldWithPath("[].edad").description("edad del cliente"),
                                fieldWithPath("[].telefono").description("telefono del cliente"),
                                fieldWithPath("[].email").description("correo electronico del cliente"),
                                subsectionWithPath("[].hired").description("datos del contrante")
                        )));
    }

    @Test
    @DisplayName("guarda un cliente")
    void save() throws Exception {
        CreateClientDTO clienteParametro = CreateClientDTO.builder().name("Nombre").edad(20).telefono("123-456-7890").email("cliente@contacto.com").build();
        ClientDTO clienteRespuesta = ClientDTO.builder().id(1L).name("Nombre").edad(20).telefono("123-456-7890").email("cliente@contacto.com").build();

        given(clientService.save(clienteParametro)).willReturn(clienteRespuesta);

        mockMvc.perform(post("/api/v1/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(clienteParametro)))
                .andExpect(status().isCreated())

                .andDo(document("clients/save",
                        requestFields(
                                fieldWithPath("name").description("nombre del cliente"),
                                fieldWithPath("edad").description("edad del cliente"),
                                fieldWithPath("telefono").description("telefono del cliente"),
                                fieldWithPath("email").description("correo electronico del cliente"),
                                subsectionWithPath("hired").description("datos del contrante")
                        ),
                        responseHeaders(
                                headerWithName("Content-Type").description("El Content-Type del recurso")
                        ))
                );
    }

    @Test
    @DisplayName("actualiza un cliente")
    void update() throws Exception {

        UpdateClientDTO clienteParametro = UpdateClientDTO.builder().name("Nombre").edad(20).telefono("123-456-7890").email("cliente@contacto.com").build();

        mockMvc.perform(put("/api/v1/clients/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(clienteParametro)))
                .andExpect(status().isNoContent())

                .andDo(document("clients/update",
                        pathParameters(
                                parameterWithName("id").description("Identificador del cliente")
                        ),
                        requestFields(
                                fieldWithPath("name").description("nombre del cliente"),
                                fieldWithPath("edad").description("edad del cliente"),
                                fieldWithPath("telefono").description("telefono del cliente"),
                                fieldWithPath("email").description("correo electronico del cliente"),
                                subsectionWithPath("hired").description("datos del contrante")
                        )
                ));
    }

    @Test
    @DisplayName("elimina un cliente")
    void deleteClient() throws Exception {
        mockMvc.perform(delete("/api/v1/clients/{id}", 1)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())

                .andDo(document("clients/delete-clients",
                        pathParameters(
                                parameterWithName("id").description("Identificador del cliente")
                        )));
    }

}