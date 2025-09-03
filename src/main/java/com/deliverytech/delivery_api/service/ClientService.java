package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.dto.ClientDTO;
import com.deliverytech.delivery_api.dto.UpdateStatusDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> findAllClients();
    long createClient(ClientDTO clientDTO);
    ClientDTO findClientByID(Long id);
    ClientDTO findClientByEmail(String email);
    ClientDTO updateClient(Long id, ClientDTO clientDTO);
    void deleteClient(Long id);
    List<ClientDTO> listActiveClients();
    void updateStatus(UpdateStatusDTO updateStatusDTO);

}