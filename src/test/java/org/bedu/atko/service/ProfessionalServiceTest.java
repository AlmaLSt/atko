package org.bedu.atko.service;

import org.assertj.core.api.Assertions;
import org.bedu.atko.dto.CategoryDTO;
import org.bedu.atko.dto.professional.CreateProfessionalDTO;
import org.bedu.atko.dto.professional.UpdateProfessionalDTO;
import org.bedu.atko.dto.ProfessionalDTO;
import org.bedu.atko.entity.Category;
import org.bedu.atko.entity.Client;
import org.bedu.atko.entity.Professional;
import org.bedu.atko.exception.ProfessionalNotFoundException;
import org.bedu.atko.mapper.IProfessionalMapper;
import org.bedu.atko.repository.IProfessionalRepository;
import org.bedu.atko.service.impl.ProfessionalServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfessionalServiceTest {

    @Mock
    private IProfessionalRepository professionalRepository;

    @Mock
    private IProfessionalMapper mapper;

    @InjectMocks
    private ProfessionalServiceImpl professionalService;

    @Test
    @DisplayName("Should save a professioal")
    void saveProfessional(){
        Category category = Category.builder()
                .name("Dentista")
                .build();
        CategoryDTO categoryDTO = CategoryDTO.builder().name("Dentista").build();
        Set<Client> employmentContracts = new HashSet<>();

        Professional professional = Professional.builder().name("Julio").edad(32).telefono("789456123").email("julio@gmail.com").areaTrabajo("CDMX").category(category).employmentContracts(employmentContracts).build();

        ProfessionalDTO professionalDTO = ProfessionalDTO.builder().name("Julio").edad(32).telefono("789456123").email("julio@gmail.com").areaTrabajo("CDMX").category(category).build();

        CreateProfessionalDTO createProfessionalDTO = CreateProfessionalDTO.builder().name("Julio").edad(32).telefono("789456123").email("julio@gmail.com").areaTrabajo("CDMX").category(category).build();

        when(professionalRepository.save(Mockito.any(Professional.class))).thenReturn(professional);
        when(mapper.toModel(createProfessionalDTO)).thenReturn(professional);
        when(mapper.toDTO(professional)).thenReturn(professionalDTO);

        ProfessionalDTO savedProfessional = professionalService.save(createProfessionalDTO);

        Assertions.assertThat(savedProfessional).isNotNull();

        verify(professionalRepository, times(1)).save(professional);
        verify(mapper, times(1)).toModel(createProfessionalDTO);
        verify(mapper, times(1)).toDTO(professional);
    }

    @Test
    @DisplayName("Should find all professionals")
    void findAllProfessionals(){
        List<Professional> professionalList = new ArrayList<>();

        when(professionalRepository.findAll()).thenReturn(professionalList);

        List<ProfessionalDTO> found = professionalService.findAll();

        Assertions.assertThat(found).isNotNull();

        verify(professionalRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should find professional by id")
    void findProfessionalById(){
        Category category = Category.builder()
                .name("Dentista")
                .build();
        CategoryDTO categoryDTO = CategoryDTO.builder().name("Dentista").build();
        Set<Client> employmentContracts = new HashSet<>();

        Professional professional = Professional.builder().id(1L).name("Julio").edad(32).telefono("789456123").email("julio@gmail.com").areaTrabajo("CDMX").category(category).employmentContracts(employmentContracts).build();

        ProfessionalDTO professionalDTO = ProfessionalDTO.builder().name("Julio").edad(32).telefono("789456123").email("julio@gmail.com").areaTrabajo("CDMX").category(category).build();

        Optional<Professional> professionalId = Optional.of(professional);

        when(professionalRepository.findById(professional.getId())).thenReturn(professionalId);
        when(mapper.toDTO(professional)).thenReturn(professionalDTO);

        Optional<ProfessionalDTO> foundId = professionalService.findById(professional.getId());

        Assertions.assertThat(foundId).isNotNull();

        verify(professionalRepository, times(1)).findById(professional.getId());
        verify(mapper, times(1)).toDTO(professional);
    }

    @Test
    @DisplayName("Should update a professional")
    void updateProfessional(){
        Category category = Category.builder()
                .name("Dentista")
                .build();
        Set<Client> employmentContracts = new HashSet<>();

        Professional professional = Professional.builder().id(1L).name("Julio").edad(32).telefono("789456123").email("julio@gmail.com").areaTrabajo("CDMX").category(category).employmentContracts(employmentContracts).build();

        UpdateProfessionalDTO updateProfessional = UpdateProfessionalDTO.builder().name("Julio Vázquez").build();

        assertThatThrownBy(() -> professionalService.update(professional.getId(), updateProfessional))
                .isInstanceOf(ProfessionalNotFoundException.class);
    }

    @Test
    @DisplayName("Should delete a professional")
    void deleteProfessional(){
        Category category = Category.builder()
                .name("Dentista")
                .build();
        Set<Client> employmentContracts = new HashSet<>();

        Professional professional = Professional.builder().id(1L).name("Julio").edad(32).telefono("789456123").email("julio@gmail.com").areaTrabajo("CDMX").category(category).employmentContracts(employmentContracts).build();

        professionalService.delete(professional.getId());

        assertAll(()-> professionalService.delete(professional.getId()));

    }

}