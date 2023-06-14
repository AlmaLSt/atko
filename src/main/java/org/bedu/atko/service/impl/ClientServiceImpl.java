package org.bedu.atko.service.impl;

import org.bedu.atko.dto.Client.CreateClientDTO;
import org.bedu.atko.dto.Client.UpdateClientDTO;
import org.bedu.atko.dto.ClientDTO;
import org.bedu.atko.exception.ClientNotFoundException;
import org.bedu.atko.mapper.IClientMapper;
import org.bedu.atko.entity.Client;
import org.bedu.atko.repository.IClientRepository;
import org.bedu.atko.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements IClientService {

    private IClientRepository repository;
    private IClientMapper mapper;

    @Autowired
    public ClientServiceImpl(IClientRepository repository, IClientMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }


    public List<ClientDTO> findAll() {
        List<Client> clients = repository.findAll();
        return clients.stream().map(mapper::toDTO).toList();
    }


    public Optional<ClientDTO> findById(long id) {
        Optional<Client> client = repository.findById(id);
        return client.isPresent() ? Optional.of(mapper.toDTO(client.get())) : Optional.of(null);
    }


    public ClientDTO save(CreateClientDTO data) {
        Client entity = repository.save(mapper.toModel(data));
        return mapper.toDTO(entity);
    }


    public void update(long id, UpdateClientDTO data) {
        Optional<Client> current = repository.findById(id);
        if(!current.isPresent()){
            throw new ClientNotFoundException();
        }
        Client client = current.get();
        mapper.toModel(client, data);
        repository.save(client);

    }


    public void delete(long id) {
        repository.deleteById(id);

    }
}
