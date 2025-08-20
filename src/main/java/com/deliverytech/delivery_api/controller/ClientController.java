package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.dto.ClientDTO;
import com.deliverytech.delivery_api.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/findAll")
    public List<ClientDTO> getAllClients(){
        return clientService.findAllClients();
    }

}
