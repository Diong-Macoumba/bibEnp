package com.enp.bibliotheque.benp.persons.repositories;

import com.enp.bibliotheque.benp.persons.entities.Agent;
import com.enp.bibliotheque.benp.persons.entities.Book;
import com.enp.bibliotheque.benp.persons.entities.Emprunte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmprunteRepository extends JpaRepository<Emprunte, Long> {
    Optional<Agent> findByBookEmp(String book);
    Optional<List<Book>> findAllByAgentEmp(String username);
}
