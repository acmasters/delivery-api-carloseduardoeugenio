package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.dto.ClientDTO;
import com.deliverytech.delivery_api.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ClientController {
    @Autowired
    private ClientServiceImpl clientServiceImpl;

    @GetMapping("/findAllClients")
    public List<ClientDTO> getAllClients(){
        return clientServiceImpl.findAllClients();
    }
}