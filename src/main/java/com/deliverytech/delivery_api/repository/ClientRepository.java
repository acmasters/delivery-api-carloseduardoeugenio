package com.deliverytech.delivery_api.repository;

import com.deliverytech.delivery_api.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}