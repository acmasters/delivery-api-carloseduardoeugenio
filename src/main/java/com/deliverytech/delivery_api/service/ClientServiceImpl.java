package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.dto.ClientDTO;
import com.deliverytech.delivery_api.dto.UpdateStatusDTO;
import com.deliverytech.delivery_api.entity.Client;
import com.deliverytech.delivery_api.exception.ConflictException;
import com.deliverytech.delivery_api.exception.DeliveryAPIException;
import com.deliverytech.delivery_api.exception.NotFoundException;
import com.deliverytech.delivery_api.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Autowired
    private ClientRepository clientRepository;

    public List<ClientDTO> findAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(this::ConvertEntitytoDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public long createClient(ClientDTO clientDTO) throws DeliveryAPIException {
            logger.info("Creating client with name {} and id: {}",clientDTO.getName(), clientDTO.getId());
            boolean emailExists = clientRepository.existsByEmail(clientDTO.getEmail());
            if (emailExists) {
                throw new ConflictException("Client already exists");
            }
            ModelMapper modelMapper = new ModelMapper();
            Client client = modelMapper.map(clientDTO, Client.class);
            return clientRepository.save(client).getId();
        }

    @Override
    public ClientDTO findClientByID(Long id) {
        logger.info("Finding client with id: {}", id);
        Client client =  clientRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Client not found with ID: %d".formatted(id)));
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(client, ClientDTO.class);

    }

    @Override
    public ClientDTO findClientByEmail(String email) {
        logger.info("Finding client with email: {}", email);
        var client = clientRepository.findClientByEmail(email).orElseThrow(()
                -> new EntityNotFoundException("Client not found with ID: %d".formatted(email)));;
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(client, ClientDTO.class);
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        var client = clientRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Client not found ID: %d".formatted(id)));
        client.setName(clientDTO.getName());
        client.setAddress(clientDTO.getAddress());
        client.setEmail(client.getEmail());
        client.setPhone(client.getPhone());
        client.setStatus(client.getStatus());
        clientRepository.save(client);

        return clientDTO;
    }

    @Override
    public void deleteClient(Long id) {
        logger.info("Deleting client with id: {}", id);
        clientRepository.deleteById(id);
    }

    @Override
    public List<ClientDTO> listActiveClients() {
        var modelMapper = new ModelMapper();
        return clientRepository.findByActiveTrue().stream().map(c->
                modelMapper.map(c, ClientDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void updateStatus(UpdateStatusDTO updateStatusDTO) {
        boolean statusUpdated = clientRepository.updateStatus(updateStatusDTO.getEmail()
                ,updateStatusDTO.isStatus());
        if (statusUpdated) {
            throw new NotFoundException(("Client not found with email: %d".formatted(updateStatusDTO.getEmail())));
        }
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