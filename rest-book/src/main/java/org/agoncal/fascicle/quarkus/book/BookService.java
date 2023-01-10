package org.agoncal.fascicle.quarkus.book;

import org.agoncal.fascicle.quarkus.book.client.IsbnNumbers;
import org.agoncal.fascicle.quarkus.book.client.NumberProxy;
// tag::adocFallbackImport[]
import org.eclipse.microprofile.faulttolerance.Fallback;
// end::adocFallbackImport[]
// tag::adocImportRestClient[]
import org.eclipse.microprofile.rest.client.inject.RestClient;
// end::adocImportRestClient[]
// tag::adocImportInit[]
import org.jboss.logging.Logger;
// end::adocImportInit[]

// tag::adocImportInit[]
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
// end::adocImportInit[]
import jakarta.json.bind.JsonbBuilder;
// tag::adocImportInit[]
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
// end::adocImportInit[]
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;
// tag::adocImportInit[]
import java.util.List;
import java.util.Optional;
// end::adocImportInit[]

// tag::adocSnippet[]
@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class BookService {

  @Inject
  Logger LOGGER;

  @Inject
  EntityManager em;

  // tag::adocRestClientAttr[]
  @Inject
  @RestClient
  NumberProxy numberProxy;
  // end::adocRestClientAttr[]

  // tag::adocFallback[]
  @Fallback(fallbackMethod = "fallbackPersistBook")
  // end::adocFallback[]
  // tag::adocPersistBook[]
  // tag::adocBeanValidation[]
  public Book persistBook(@Valid Book book) {
    // tag::adocRestClient[]
    // The Book microservice invokes the Number microservice
    IsbnNumbers isbnNumbers = numberProxy.generateNumbers();
    book.isbn13 = isbnNumbers.isbn13;
    book.isbn10 = isbnNumbers.isbn10;

    // end::adocRestClient[]
    Book.persist(book);
    return book;
  }
  // end::adocBeanValidation[]
  // end::adocPersistBook[]

  // tag::adocFallback[]

  private Book fallbackPersistBook(Book book) throws FileNotFoundException {
    LOGGER.warn("Falling back on persisting a book");
    book.id = 0L;
    book.isbn13 = "to be fixed";
    book.isbn10 = "to be fixed";
    String bookJson = JsonbBuilder.create().toJson(book);
    try (PrintWriter out = new PrintWriter("book-" + Instant.now().toEpochMilli() + ".json")) {
      out.println(bookJson);
    }
    throw new IllegalStateException();
  }

  // end::adocFallback[]
  @Transactional(Transactional.TxType.SUPPORTS)
  public List<Book> findAllBooks() {
    return Book.listAll();
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public Optional<Book> findBookById(Long id) {
    return Book.findByIdOptional(id);
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public Book findRandomBook() {
    Book randomBook = null;
    while (randomBook == null) {
      randomBook = Book.findRandom();
    }
    return randomBook;
  }
  // tag::adocBeanValidation[]

  public Book updateBook(@Valid Book book) {
    Book entity = em.merge(book);
    return entity;
  }
  // end::adocBeanValidation[]

  public void deleteBook(Long id) {
    Book.deleteById(id);
  }
}
// end::adocSnippet[]
