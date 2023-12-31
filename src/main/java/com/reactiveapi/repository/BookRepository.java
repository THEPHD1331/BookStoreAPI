package com.reactiveapi.repository;

import com.reactiveapi.entity.Book;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, Integer> {

    @Query("select * from book_details where name LIKE:title")
    Flux<Book> findByName(String title);
    Flux<Book> findByAuthor(String author);
    Flux<Book> findByPublisher(String publisher);
    Flux<Book> findByNameAndAuthor(String name, String author);
}
