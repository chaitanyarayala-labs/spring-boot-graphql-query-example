package com.graphql.springbootgrapqlexample.service;

import com.graphql.springbootgrapqlexample.model.Author;
import com.graphql.springbootgrapqlexample.model.Book;
import com.graphql.springbootgrapqlexample.repository.AuthorRepository;
import com.graphql.springbootgrapqlexample.repository.BookRepository;
import com.graphql.springbootgrapqlexample.service.datafetcher.AllAuthorsDataFetcher;
import com.graphql.springbootgrapqlexample.service.datafetcher.AllBooksDataFetcher;
import com.graphql.springbootgrapqlexample.service.datafetcher.AuthorDataFetcher;
import com.graphql.springbootgrapqlexample.service.datafetcher.BookDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

@Service
public class GraphQLService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Value("classpath:books.graphql")
    Resource resource;

    private GraphQL graphQL;
    @Autowired
    private AllBooksDataFetcher allBooksDataFetcher;
    @Autowired
    private BookDataFetcher bookDataFetcher;
    @Autowired
    private AllAuthorsDataFetcher allAuthorsDataFetcher;
    @Autowired
    private AuthorDataFetcher authorDataFetcher;

    // load schema at application start up  //NOSONAR
    @PostConstruct
    private void loadSchema() throws IOException {

        //Load Books into the Book Repository   //NOSONAR
        loadDataIntoHSQL();

        // get the schema   //NOSONAR
        File schemaFile = resource.getFile();
        // parse schema     //NOSONAR
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private void loadDataIntoHSQL() {

        Stream.of(
                new Book("123", "Book of Clouds", "Kindle Edition",
                    Collections.singletonList(new Author("567", "Chloe", "Aridis"))
                        , "Nov 2017"),
                new Book("124", "Cloud Arch & Engineering", "Orielly",
                        Arrays.asList(
                            new Author("891", "Peter", "Russels"),
                            new Author("890", "Sam",  "Bugati")),
                    "Jan 2015"),
                new Book("125", "Java 9 Programming", "Orielly",
                        Arrays.asList(
                            new Author("901", "Venkat", "Thummala"),
                            new Author("911", "Ram", "Bopanna")
                        ), "Dec 2016")
        ).forEach(book -> {
            book.getAuthors().forEach(author -> authorRepository.save(author));
            bookRepository.save(book);
        });
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allBooks", allBooksDataFetcher)
                        .dataFetcher("book", bookDataFetcher)
                        .dataFetcher("allAuthors", allAuthorsDataFetcher)
                        .dataFetcher("author", authorDataFetcher))
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
