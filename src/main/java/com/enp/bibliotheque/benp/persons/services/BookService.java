package com.enp.bibliotheque.benp.persons.services;

import com.enp.bibliotheque.benp.exceptions.NotFoundException;
import com.enp.bibliotheque.benp.persons.entities.Book;
import com.enp.bibliotheque.benp.persons.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService{
    @Autowired
    private BookRepository bookRepository;

    public Page<Book> findAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Book saveBook(Book book) {
        Optional<Book> bookToSave =  bookRepository.findByName(book.getName());
        if(bookToSave.isPresent()) { 
            throw new EntityNotFoundException("Bookis already exits");
        }
        return bookRepository.save(book);
    }

    public Book findBookById(Long id)  {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No book with id "+id));
    }
    public Book updateBook(Long id, Book book) {
        Book bookToUpdate = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No book with id "+id));
        bookToUpdate.setName(book.getName());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setStock(book.getStock());
        bookToUpdate.setFormat(book.getFormat());
        return bookRepository.save(bookToUpdate);
    }

    public void deleteBook(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No book with id "+id));
        bookRepository.delete(book);
    }

    public Book findBookByName(String name) {
        return bookRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("No book with name " +name));
    }

    public List<Book> findBookByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }
}
