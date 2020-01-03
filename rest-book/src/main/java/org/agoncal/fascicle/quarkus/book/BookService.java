package org.agoncal.fascicle.quarkus.book;

import org.agoncal.fascicle.quarkus.book.client.IsbnNumbers;
import org.agoncal.fascicle.quarkus.book.client.IsbnNumbersService;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

// tag::adocSnippet[]
@ApplicationScoped
@Transactional(REQUIRED)
public class BookService {

  // tag::adocConfigProperty[]
  @ConfigProperty(name = "book.discount", defaultValue = "1")
  BigDecimal discount;
  // end::adocConfigProperty[]

  @Inject
  @RestClient
  IsbnNumbersService isbnNumbersService;

  // tag::adocPersistBook[]
  public Book persistBook(@Valid Book book) {
    // tag::adocConfigProperty[]
    book.price = book.price.multiply(discount);
    // end::adocConfigProperty[]
    IsbnNumbers isbnNumbers = isbnNumbersService.generateIsbnNumbers();
    book.isbn13 = isbnNumbers.getIsbn13();
    book.isbn10 = isbnNumbers.getIsbn10();
    Book.persist(book);
    return book;
  }
  // end::adocPersistBook[]

  @Transactional(SUPPORTS)
  public List<Book> findAllBooks() {
    return Book.listAll();
  }

  @Transactional(SUPPORTS)
  public Book findBookById(Long id) {
    return Book.findById(id);
  }

  @Transactional(SUPPORTS)
  public Book findRandomBook() {
    Book randomBook = null;
    while (randomBook == null) {
      randomBook = Book.findRandom();
    }
    return randomBook;
  }

  public Book updateBook(@Valid Book book) {
    Book entity = Book.findById(book.id);
    entity.title = book.title;
    entity.isbn13 = book.isbn13;
    entity.isbn10 = book.isbn10;
    entity.author = book.author;
    entity.yearOfPublication = book.yearOfPublication;
    entity.nbOfPages = book.nbOfPages;
    entity.rank = book.rank;
    entity.price = book.price;
    entity.smallImageUrl = book.smallImageUrl;
    entity.mediumImageUrl = book.mediumImageUrl;
    entity.description = book.description;
    return entity;
  }

  public void deleteBook(Long id) {
    Book book = Book.findById(id);
    book.delete();
  }
}
// end::adocSnippet[]
