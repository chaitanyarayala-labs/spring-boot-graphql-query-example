# Spring Boot with GraphQL Query Example

Update: Upgraded to Java 11 and Graph QL to 5+ version dependency

## Book Store
- `/rest/books` is the REST resource which can fetch Books information
- DataFetchers are Interfaces for RuntimeWiring of GraphQL with JpaRepository

## Sample GraphQL Scalar Queries
- Accessible under `http://localhost:8091/rest/books`
- Usage for `allBooks` with authors
```
{
   allBooks {
     isn
     title
     authors {
        id
        firstName
        lastName
     }
     publisher
   }
 }
```
- Usage for `book` with authors
```
  {
   book(id: "123") {
     title
     authors {
        id
        firstName
        lastName
     }
     publisher
   }
```
- Combination of both `allBooks` and `book` with authors
```
{
   allBooks {
     title
     authors {
        id
        firstName
        lastName
     }
   }
   book(id: "124") {
     title
     authors {
        id
        firstName
        lastName
     }
     publisher
   }
 }
```
