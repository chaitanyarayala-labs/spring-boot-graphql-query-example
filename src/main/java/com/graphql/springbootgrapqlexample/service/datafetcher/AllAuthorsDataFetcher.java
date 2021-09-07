package com.graphql.springbootgrapqlexample.service.datafetcher;

import com.graphql.springbootgrapqlexample.model.Author;
import com.graphql.springbootgrapqlexample.repository.AuthorRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllAuthorsDataFetcher implements DataFetcher<List<Author>>{

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public List<Author> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return authorRepository.findAll();
    }
}
