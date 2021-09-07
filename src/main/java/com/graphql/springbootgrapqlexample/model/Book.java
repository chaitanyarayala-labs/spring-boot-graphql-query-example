package com.graphql.springbootgrapqlexample.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table
@Entity
public class Book {

    @Id
    private String isn;
    private String title;
    private String publisher;
    @OneToMany
    private List<Author> authors;
    private String publishedDate;

}
