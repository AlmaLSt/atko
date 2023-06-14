package org.bedu.atko.service.impl;

import org.bedu.atko.dto.Professional.CreateProfessionalDTO;
import org.bedu.atko.dto.Professional.UpdateProfessionalDTO;
import org.bedu.atko.dto.ProfessionalDTO;
import org.bedu.atko.entity.Professional;
import org.bedu.atko.exception.ProfessionalNotFoundException;
import org.bedu.atko.mapper.IProfessionalMapper;
import org.bedu.atko.repository.IProfessionalRepository;
import org.bedu.atko.service.IProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessionalServiceImpl implements IProfessionalService {
    private IProfessionalRepository repository;
    private IProfessionalMapper mapper;

    @Autowired
    public ProfessionalServiceImpl(IProfessionalRepository repository, IProfessionalMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ProfessionalDTO> findAll(){
        List<Professional> professionals = repository.findAll();
        return professionals.stream().map(mapper::toDTO).toList();
    }

    public Optional<ProfessionalDTO> findById(long id) {
        Optional<Professional> professional = repository.findById(id);
        return professional.isPresent() ? Optional.of(mapper.toDTO(professional.get())) : Optional.of(null);
    }

    public ProfessionalDTO save(CreateProfessionalDTO data){
        Professional entity = repository.save(mapper.toModel(data));
        return mapper.toDTO(entity);
    }

    public void update(long id, UpdateProfessionalDTO data) {
        Optional<Professional> current = repository.findById(id);
        if (!current.isPresent()) {
            throw new ProfessionalNotFoundException();
        }
        Professional professional = current.get();
        mapper.toModel(professional, data);
        repository.save(professional);
    }
    public void delete(long id){
        repository.deleteById(id);
    }
}
