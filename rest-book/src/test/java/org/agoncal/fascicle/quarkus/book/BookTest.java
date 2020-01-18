package org.agoncal.fascicle.quarkus.book;

import java.util.List;

public class BookTest {

  private Long id;

  public void shouldCRUD() {
    // tag::adocSnippet[]
    // creating a book
    Book book = new Book();
    book.title = "H2G2";
    book.rank = 9;

    // persist it
    book.persist();

    // getting a list of all Book entities
    List<Book> books = Book.listAll();

    // finding a specific book by ID
    book = Book.findById(id);

    // counting all books
    long countAll = Book.count();
    // end::adocSnippet[]
  }

}
