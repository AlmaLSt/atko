package org.bedu.atko.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bedu.atko.dto.category.CreateCategoryDTO;
import org.bedu.atko.dto.category.UpdateCategoryDTO;
import org.bedu.atko.dto.CategoryDTO;
import org.bedu.atko.service.ICategoryService;
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
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureRestDocs
@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICategoryService categoryService;

    @Test
    @DisplayName("obtiene todas las categorías")
    void findAll() throws Exception {

        List<CategoryDTO> categorias = Arrays.asList(
                CategoryDTO.builder().id(1L).name("Nombre").build(),
                CategoryDTO.builder().id(2L).name("Nombre2").build(),
                CategoryDTO.builder().id(3L).name("Nombre3").build()
        );

        given(categoryService.findAll()).willReturn(categorias);

        mockMvc.perform(get("/api/v1/categories")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[0].name", is("Nombre")))
                .andExpect(jsonPath("$[2].name", is("Nombre3")))

                .andDo(document("categories/findAll",
                        responseFields(
                                fieldWithPath("[].id").description("identificador de la categoría"),
                                fieldWithPath("[].name").description("nombre de la categoría")
                        )));
    }

    @Test
    @DisplayName("obtiene una categoría por su id")
    void findByID() throws Exception {
        given(categoryService.findById(anyLong())).willReturn(Optional.of(CategoryDTO.builder().id(1L).name("Nombre").build()));

        mockMvc.perform(get("/api/v1/categories/{id}", 1)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Nombre")))

                .andDo(document("categories/findByID",
                        pathParameters(
                                parameterWithName("id").description("Identificador de la categoría")
                        ),
                        responseFields(
                                fieldWithPath("id").description("identificador de la categoría"),
                                fieldWithPath("name").description("nombre de la categoría")

                        )));
    }

    @Test
    @DisplayName("guarda una categoría")
    void save() throws Exception {
        CreateCategoryDTO categoriaParametro = CreateCategoryDTO.builder().name("Nombre").build();
        CategoryDTO clienteRespuesta = CategoryDTO.builder().id(1L).name("Nombre").build();

        given(categoryService.save(categoriaParametro)).willReturn(clienteRespuesta);

        mockMvc.perform(post("/api/v1/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(categoriaParametro)))
                .andExpect(status().isCreated())

                .andDo(document("categories/save",
                        requestFields(
                                fieldWithPath("name").description("nombre de la categoría")
                        ),
                        responseHeaders(
                                headerWithName("Content-Type").description("El Content-Type del recurso")
                        ))
                );
    }

    @Test
    @DisplayName("actualiza una categoría")
    void update() throws Exception {

        UpdateCategoryDTO categoriaParametro = UpdateCategoryDTO.builder().name("Nombre").build();

        mockMvc.perform(put("/api/v1/categories/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(categoriaParametro)))
                .andExpect(status().isNoContent())

                .andDo(document("categories/update",
                        pathParameters(
                                parameterWithName("id").description("Identificador de la categoría")
                        ),
                        requestFields(
                                fieldWithPath("name").description("nombre de la categoría")
                        )
                ));
    }

    @Test
    @DisplayName("elimina una categoría")
    void deleteClient() throws Exception {
        mockMvc.perform(delete("/api/v1/categories/{id}", 1)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())

                .andDo(document("categories/delete-categories",
                        pathParameters(
                                parameterWithName("id").description("Identificador de la categoría")
                        )));
    }
}