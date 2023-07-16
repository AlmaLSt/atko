package org.bedu.atko.service;

import org.bedu.atko.dto.client.CreateClientDTO;
import org.bedu.atko.dto.client.UpdateClientDTO;
import org.bedu.atko.dto.ClientDTO;

import java.util.List;
import java.util.Optional;

public interface IClientService {
    List<ClientDTO> findAll();
    Optional<ClientDTO> findById(long id);

    ClientDTO save(CreateClientDTO data);

    void update(long id, UpdateClientDTO data);

    void delete(long id);
}