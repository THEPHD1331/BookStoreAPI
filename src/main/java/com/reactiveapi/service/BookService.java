package com.reactiveapi.service;

import com.reactiveapi.entity.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {

    Flux<Book> getAllBooks();
    Mono<Book> getBook(int bookId);
    Mono<Book> createBook(Book book);
    Mono<Book> updateBook(Book book, int bookId);
    Mono<Void> deleteBook(int bookId);
    Flux<Book> searchBooks(String bookName);
}
