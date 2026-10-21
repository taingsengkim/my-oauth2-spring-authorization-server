package co.istad.oauth2springauthorizationserver2.repository;

import java.util.Optional;


import co.istad.oauth2springauthorizationserver2.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByClientId(String clientId);
}