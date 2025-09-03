package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.dto.ClientDTO;
import com.deliverytech.delivery_api.dto.UpdateStatusDTO;
import com.deliverytech.delivery_api.service.ClientServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/clients")
@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Client created"),
        @ApiResponse(responseCode = "400", description = "Invalid data"),
        @ApiResponse(responseCode = "409", description = "Client already exists"
        )})
public class ClientController {
    @Autowired
    private ClientServiceImpl clientServiceImpl;

    @GetMapping("/findAllClients")
    public List<ClientDTO> findAllClients() {
        return clientServiceImpl.findAllClients();
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<ClientDTO> findByClientEmail(String email) {
        ClientDTO clientDTO =  clientServiceImpl.findClientByEmail(email);
        return ResponseEntity.ok(clientDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Long> createClient(@Valid @RequestBody ClientDTO clientDTO) {
        var id = clientServiceImpl.createClient(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PatchMapping("/{id}/client")
    public ResponseEntity<ClientDTO> updateClient(Long id, @RequestBody ClientDTO clientDTO) {
        var clientUpdated =  clientServiceImpl.updateClient(id, clientDTO);
        return ResponseEntity.ok(clientUpdated);
    }

    @PatchMapping("/updateClientStatus")
    public ResponseEntity<String> updateStatus( @Valid @RequestBody UpdateStatusDTO updateStatusDTO) {
        clientServiceImpl.updateStatus(updateStatusDTO);
        return ResponseEntity.ok("Update Client status with success");
    }

    @DeleteMapping("/{id}/client")
    public ResponseEntity<Void> deleteClient(Long id) {
        clientServiceImpl.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}