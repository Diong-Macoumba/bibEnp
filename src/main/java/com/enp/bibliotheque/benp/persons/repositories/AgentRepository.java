package com.enp.bibliotheque.benp.persons.repositories;


import com.enp.bibliotheque.benp.persons.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent, Long> {
    Optional<Agent> findByCcap(String ccap);
    Optional<Agent> findByEmail(String email);
    Optional<Agent> findByTelephone(String teletelephone);
}
