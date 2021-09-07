package com.graphql.springbootgrapqlexample.service.datafetcher;

import com.graphql.springbootgrapqlexample.model.Author;
import com.graphql.springbootgrapqlexample.repository.AuthorRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorDataFetcher implements DataFetcher<Author>{

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public Author get(DataFetchingEnvironment dataFetchingEnvironment) {

        String id = dataFetchingEnvironment.getArgument("id");

        return authorRepository.findById(id).get();
    }
}
