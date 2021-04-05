package com.enp.bibliotheque.benp.persons.repositories;


import com.enp.bibliotheque.benp.persons.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByName(String name);
    List<Book> findByAuthor(String author);
}
