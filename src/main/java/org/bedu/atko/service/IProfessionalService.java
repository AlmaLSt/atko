package org.bedu.atko.service;

import org.bedu.atko.dto.Professional.CreateProfessionalDTO;
import org.bedu.atko.dto.Professional.UpdateProfessionalDTO;
import org.bedu.atko.dto.ProfessionalDTO;
import org.bedu.atko.entity.Professional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface IProfessionalService {
    List<ProfessionalDTO> findAll();
    Optional<ProfessionalDTO> findById(long id);
    ProfessionalDTO save(CreateProfessionalDTO data);
    void update(long id, UpdateProfessionalDTO data);
    void delete(long id);
}
