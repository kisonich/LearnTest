package com.kisonich.learntest.magaz.controllers;


import com.kisonich.learntest.magaz.exception.BookNotFoundException;
import com.kisonich.learntest.magaz.model.Book;
import com.kisonich.learntest.magaz.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable long id) throws BookNotFoundException {
        return bookService.getBook(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable long id, @RequestBody Book updatedBook) throws BookNotFoundException {
        return bookService.updateBook(id, updatedBook);
    }
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id) throws BookNotFoundException {
        bookService.deleteBook(id);
    }
}