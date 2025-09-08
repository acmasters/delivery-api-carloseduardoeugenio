package com.deliverytech.delivery_api.repository;

import com.deliverytech.delivery_api.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByEmail(String email);
    Optional<Client> findClientByEmail(String email);
    List<Client>findByActiveTrue();
    List<Client>findByNameContainingIgnoreCase(String name);

    @Modifying
    @Query("UPDATE Client c SET c.active = :status WHERE c.email = :email")
    boolean updateStatus(@Param("email") String email, @Param("status") boolean status);


}