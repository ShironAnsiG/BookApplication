package com.example.BookApplication.Service;

import com.example.BookApplication.Entity.Book;
import com.example.BookApplication.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    public Book updateBook(Integer id, Book bookDetails) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(bookDetails.getTitle());
                    existingBook.setAuthor(bookDetails.getAuthor());
                    existingBook.setGenre(bookDetails.getGenre());
                    return bookRepository.save(existingBook);
                })
                .orElseGet(() -> {
                    bookDetails.setId(id);
                    return bookRepository.save(bookDetails);
                });
    }

    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);

    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
