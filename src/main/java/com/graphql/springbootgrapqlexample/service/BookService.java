package com.graphql.springbootgrapqlexample.service;

import com.graphql.springbootgrapqlexample.repository.BookRepository;
import com.graphql.springbootgrapqlexample.model.Book;
import com.graphql.springbootgrapqlexample.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public void addBook(Book book) {
        if (!book.getAuthors().isEmpty()) {
            book.getAuthors().forEach(author -> authorRepository.save(author));
        }
        bookRepository.save(book);
    }
}
