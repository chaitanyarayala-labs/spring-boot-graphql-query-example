package com.graphql.springbootgrapqlexample.controller;

import com.graphql.springbootgrapqlexample.model.Book;
import com.graphql.springbootgrapqlexample.service.BookService;
import com.graphql.springbootgrapqlexample.service.GraphQLService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest")
@RestController
public class BookResource {

    @Autowired
    BookService bookService;

    @Autowired
    GraphQLService graphQLService;

    @PostMapping(path = "/book")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        bookService.addBook(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PostMapping(path = "/graphql")
    public ResponseEntity<Object> getAllBooks(@RequestBody String query) {
        ExecutionResult execute = graphQLService.getGraphQL().execute(query);

        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
}
