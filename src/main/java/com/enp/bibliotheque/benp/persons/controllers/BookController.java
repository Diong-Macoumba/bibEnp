package com.enp.bibliotheque.benp.persons.controllers;

import com.enp.bibliotheque.benp.persons.entities.Book;
import com.enp.bibliotheque.benp.persons.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping(value = "/create")
    public Book createBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @GetMapping
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookService.findAllBooks(pageable);
    }

    @GetMapping(value = "/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @PutMapping("/update")
    public Book updateBook(@RequestParam Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @GetMapping(params = "author")
    public List<Book> getBookByAuthor(@RequestParam String author){
        return bookService.findBookByAuthor(author);
    }

    @DeleteMapping("/delete")
    public void deleteBook(@RequestParam Long id)        {
        bookService.deleteBook(id);
    }

    @GetMapping(params = "name")
    public Book getBookByName(@RequestParam String name){
        return bookService.findBookByName(name);
    }

}
