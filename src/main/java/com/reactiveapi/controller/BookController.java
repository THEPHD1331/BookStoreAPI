package com.reactiveapi.controller;

import com.reactiveapi.entity.Book;
import com.reactiveapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    public Flux<Book> getAllBooks(){
        return bookService.getAllBooks();
//                .delayElements(Duration.ofSeconds(2))
//                .log()
//                .map(book -> {
//                    book.setName(book.getName().toUpperCase());
//                    return book;
//                });
    }

    @GetMapping("/{bookId}")
    public Mono<Book> getBook(@PathVariable int bookId){
        return bookService.getBook(bookId);
    }

    @PostMapping
    public Mono<Book> createBook(@RequestBody Book book){
        return bookService.createBook(book);
    }

    @PutMapping("/{bookId}")
    public Mono<Book> updateBook(@RequestBody Book book, @PathVariable int bookId){
        return bookService.updateBook(book, bookId);
    }

    @DeleteMapping("/{bookId}")
    public Mono<Void> deleteBook(@PathVariable int bookId){
        return bookService.deleteBook(bookId);
    }

    @GetMapping("/search")
    public Flux<Book> searchBookByName(@RequestParam("bookName") String bookName){
        return bookService.searchBooks(bookName);
    }
}
