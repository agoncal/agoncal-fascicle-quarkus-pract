package org.agoncal.fascicle.quarkus.book;

import org.agoncal.fascicle.quarkus.book.client.IsbnNumbers;
import org.agoncal.fascicle.quarkus.book.client.NumberProxy;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RestClient;
// tag::adocImportInit[]
import org.jboss.logging.Logger;
// end::adocImportInit[]

// tag::adocImportInit[]
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
// end::adocImportInit[]
import javax.json.bind.JsonbBuilder;
// tag::adocImportInit[]
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
// end::adocImportInit[]
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

// tag::adocSnippet[]
@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class BookService {

  private static final Logger LOGGER = Logger.getLogger(BookService.class);

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
    book.isbn13 = isbnNumbers.getIsbn13();
    book.isbn10 = isbnNumbers.getIsbn10();

    // end::adocRestClient[]
    Book.persist(book);
    return book;
  }
  // end::adocBeanValidation[]
  // end::adocPersistBook[]

  // tag::adocFallback[]

  private Book fallbackPersistBook(Book book) throws FileNotFoundException {
    LOGGER.warn("Falling back on persisting a book");
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
