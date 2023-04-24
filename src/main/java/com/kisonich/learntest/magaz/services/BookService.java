package com.kisonich.learntest.magaz.services;

import com.kisonich.learntest.magaz.model.Book;
import com.kisonich.learntest.magaz.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long bookId, Book bookDetails) {
        Book book = getBook(bookId);
        book.setName(bookDetails.getName());
        book.setAuthor(bookDetails.getAuthor());
        book.setQuantity(bookDetails.getQuantity());
        book.setPrice(bookDetails.getPrice());
        return bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

//    public List<Book> searchBooks(String author, String name) {
//        if (StringUtils.isEmpty(name) && !StringUtils.isEmpty(author)) { // возможон не тот пакет StringUtils
//            return bookRepository.findByAuthorContainingIgnoreCase(author);
//        } else if (!StringUtils.isEmpty(name) && StringUtils.isEmpty(author)) {
//            return bookRepository.findByNameContainingIgnoreCase(name);
//        } else if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(author)) {
//            return bookRepository.findByAuthorContainingIgnoreCaseAndNameContainingIgnoreCase(author, name);
//        } else {
//            return Collections.emptyList();
//        }
//    }
}

//@Service
//public class BookService {
//
//    @Autowired
//    private BookRepository bookRepository;
//
//    public List<Book> getAllBooks() {
//        return bookRepository.findAll();
//    }
//
//    public Book getBookById(long id) throws BookNotFoundException {
//        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
//    }
//
//    public Book createBook(Book book) {
//        return bookRepository.save(book);
//    }
//
//    public Book updateBook(long id, Book updatedBook) throws BookNotFoundException {
//        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
//        book.setName(updatedBook.getName());
//        book.setAuthor(updatedBook.getAuthor());
//        book.setQuantity(updatedBook.getQuantity());
//        book.setPrice(updatedBook.getPrice());
//        return bookRepository.save(book);
//    }
//
//    public void deleteBook(long id) throws BookNotFoundException {
//        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
//        bookRepository.delete(book);
//    }
//}