package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.dto.ClientDTO;
import com.deliverytech.delivery_api.entity.Client;
import com.deliverytech.delivery_api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    public ClientService(){
        super();
    }
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public List<ClientDTO> findAllClients() {
        return repository.findAll()
                .stream()
                .map(this::ConvertEntitytoDTO)
                .collect(Collectors.toList());

    }

    private ClientDTO ConvertEntitytoDTO(Client entity) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName(entity.getName());
        clientDTO.setEmail(entity.getEmail());
        clientDTO.setPhone(entity.getPhone());
        clientDTO.setAddress(entity.getAddress());
        clientDTO.setActive(entity.isActive());

        return clientDTO;
    }

}
