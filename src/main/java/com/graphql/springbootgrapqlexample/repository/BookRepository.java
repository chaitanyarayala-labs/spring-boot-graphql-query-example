package com.graphql.springbootgrapqlexample.repository;

import com.graphql.springbootgrapqlexample.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
}
