package com.reactiveapi.service;

import com.reactiveapi.entity.Book;
import com.reactiveapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookRepository bookRepo;

    @Override
    public Flux<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    public Mono<Book> getBook(int bookId) {
        return bookRepo.findById(bookId);
    }

    @Override
    public Mono<Book> createBook(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public Mono<Book> updateBook(Book book, int bookId) {

        Mono<Book> oldBook= bookRepo.findById(bookId);
        oldBook.flatMap(book1 -> {

            book1.setName(book.getName());
            book1.setDescription(book.getDescription());
            book1.setPublisher(book.getPublisher());
            book1.setAuthor(book.getAuthor());
            return bookRepo.save(book1);
        });
        return null;
    }

    @Override
    public Mono<Void> deleteBook(int bookId) {
        return bookRepo.findById(bookId).
                flatMap(book -> bookRepo.delete(book));
    }

    @Override
    public Flux<Book> searchBooks(String bookName) {
        return this.bookRepo.findByName("%" + bookName + "%");
    }
}
