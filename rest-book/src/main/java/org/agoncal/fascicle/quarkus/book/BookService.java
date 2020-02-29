package org.agoncal.fascicle.quarkus.book;

import org.agoncal.fascicle.quarkus.book.client.IsbnNumbers;
import org.agoncal.fascicle.quarkus.book.client.IsbnNumbersService;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

// tag::adocSnippet[]
@ApplicationScoped
@Transactional(REQUIRED)
public class BookService {

  private static final Logger LOGGER = Logger.getLogger(BookService.class);
  // tag::adocFaultTolerance[]

  @Inject
  @RestClient
  IsbnNumbersService isbnNumbersService;

  // end::adocFaultTolerance[]
  // tag::adocFallback[]
  @Fallback(fallbackMethod = "fallbackPersistBook")
  // end::adocFallback[]
  // tag::adocPersistBook[]
  // tag::adocBeanValidation[]
  public Book persistBook(@Valid Book book) {
    // tag::adocFaultTolerance[]
    // The Book microservice invokes the Number microservice
    IsbnNumbers isbnNumbers = isbnNumbersService.generateIsbnNumbers();
    book.isbn13 = isbnNumbers.getIsbn13();
    book.isbn10 = isbnNumbers.getIsbn10();

    // end::adocFaultTolerance[]
    Book.persist(book);
    return book;
  }
  // end::adocBeanValidation[]
  // end::adocPersistBook[]

  // tag::adocFallback[]

  private Book fallbackPersistBook(Book book) throws FileNotFoundException {
    LOGGER.warn("Falling back on persisting a book");
    String bookJson = JsonbBuilder.create().toJson(book);
    try (PrintWriter out = new PrintWriter("book-" + Instant.now() + ".json")) {
      out.println(bookJson);
    }
    throw new IllegalStateException();
  }

  // end::adocFallback[]
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
  // tag::adocBeanValidation[]

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
  // end::adocBeanValidation[]

  public void deleteBook(Long id) {
    Book book = Book.findById(id);
    book.delete();
  }
}
// end::adocSnippet[]
