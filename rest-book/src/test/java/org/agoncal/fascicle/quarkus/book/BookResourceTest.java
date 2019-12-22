package org.agoncal.fascicle.quarkus.book;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

// tag::adocResourceTest[]
@QuarkusTest
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookResourceTest {

  private static final String DEFAULT_TITLE = "Title";
  private static final String UPDATED_TITLE = "Title (updated)";
  private static final String DEFAULT_ISBN = "Isbn";
  private static final String UPDATED_ISBN = "Isbn (updated)";
  private static final String DEFAULT_AUTHOR = "Author";
  private static final String UPDATED_AUTHOR = "Author (updated)";
  private static final Integer DEFAULT_YEAR_OF_PUBLICATION = 1111;
  private static final Integer UPDATED_YEAR_OF_PUBLICATION = 2222;
  private static final String DEFAULT_GENRE = "Genre";
  private static final String UPDATED_GENRE = "Genre (updated)";
  private static final BigDecimal DEFAULT_PRICE = new BigDecimal(11);
  private static final BigDecimal UPDATED_PRICE = new BigDecimal(22);

  private static final int NB_BOOKS = 951;
  private static String bookId;

  @Container
  private static final PostgreSQLContainer DATABASE = new PostgreSQLContainer<>("postgres:12.1")
    .withDatabaseName("books_database")
    .withUsername("book")
    .withPassword("book")
    .withExposedPorts(5432);

  @BeforeAll
  private static void configure() {
    System.setProperty("quarkus.datasource.url", DATABASE.getJdbcUrl());
  }

  @AfterAll
  private static void cleanup() {
    System.clearProperty("quarkus.datasource.url");
  }

  @Test
  public void shouldSayPing() {
    given()
      .when().get("/api/books/ping")
      .then()
      .statusCode(200)
      .body(is("ping"));
  }

  // tag::adocOpenAPI[]
  @Test
  void shouldPingOpenAPI() {
    given()
      .header(ACCEPT, APPLICATION_JSON)
      .when().get("/openapi")
      .then()
      .statusCode(OK.getStatusCode());
  }

  @Test
  void shouldPingSwaggerUI() {
    given()
      .when().get("/swagger-ui")
      .then()
      .statusCode(OK.getStatusCode());
  }
  // end::adocOpenAPI[]

  // tag::adocHealth[]
  @Test
  void shouldPingLiveness() {
    given()
      .when().get("/health/live")
      .then()
      .statusCode(OK.getStatusCode());
  }

  @Test
  void shouldPingReadiness() {
    given()
      .when().get("/health/ready")
      .then()
      .statusCode(OK.getStatusCode());
  }
  // end::adocHealth[]

  // tag::adocMetrics[]
  @Test
  void shouldPingMetrics() {
    given()
      .header(ACCEPT, APPLICATION_JSON)
      .when().get("/metrics/application")
      .then()
      .statusCode(OK.getStatusCode());
  }
  // end::adocMetrics[]

  @Test
  public void shouldNotFindDummy() {
    given()
      .when().get("/api/books/dummy")
      .then()
      .statusCode(404);
  }

  @Test
  void shouldNotGetUnknownBook() {
    Long randomId = new Random().nextLong();
    given()
      .pathParam("id", randomId)
      .when().get("/api/books/{id}")
      .then()
      .statusCode(NO_CONTENT.getStatusCode());
  }

  @Test
  void shouldGetRandomBook() {
    given()
      .when().get("/api/books/random")
      .then()
      .statusCode(OK.getStatusCode())
      .header(CONTENT_TYPE, APPLICATION_JSON);
  }

  @Test
  void shouldNotAddInvalidItem() {
    Book book = new Book();
    book.title = null;
    book.isbn = DEFAULT_ISBN;
    book.author = DEFAULT_AUTHOR;
    book.yearOfPublication = DEFAULT_YEAR_OF_PUBLICATION;
    book.genre = DEFAULT_GENRE;
    book.price = DEFAULT_PRICE;

    given()
      .body(book)
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .header(ACCEPT, APPLICATION_JSON)
      .when()
      .post("/api/books")
      .then()
      .statusCode(BAD_REQUEST.getStatusCode());
  }

  @Test
  @Order(1)
  void shouldGetInitialItems() {
    List<Book> books = get("/api/books").then()
      .statusCode(OK.getStatusCode())
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
      .extract().body().as(getBookTypeRef());
    assertEquals(NB_BOOKS, books.size());
  }

  @Test
  @Order(2)
  void shouldAddAnItem() {
    Book book = new Book();
    book.title = DEFAULT_TITLE;
    book.isbn = DEFAULT_ISBN;
    book.author = DEFAULT_AUTHOR;
    book.yearOfPublication = DEFAULT_YEAR_OF_PUBLICATION;
    book.genre = DEFAULT_GENRE;
    book.price = DEFAULT_PRICE;

    String location = given()
      .body(book)
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .header(ACCEPT, APPLICATION_JSON)
      .when()
      .post("/api/books")
      .then()
      .statusCode(CREATED.getStatusCode())
      .extract().header("Location");
    assertTrue(location.contains("/api/books"));

    // Stores the id
    String[] segments = location.split("/");
    bookId = segments[segments.length - 1];
    assertNotNull(bookId);

    given()
      .pathParam("id", bookId)
      .when().get("/api/books/{id}")
      .then()
      .statusCode(OK.getStatusCode())
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .body("title", Is.is(DEFAULT_TITLE))
      .body("isbn", Is.is(DEFAULT_ISBN))
      .body("author", Is.is(DEFAULT_AUTHOR))
      .body("yearOfPublication", Is.is(DEFAULT_YEAR_OF_PUBLICATION))
      .body("genre", Is.is(DEFAULT_GENRE))
      .body("price", Is.is(DEFAULT_PRICE));

    List<Book> books = get("/api/books").then()
      .statusCode(OK.getStatusCode())
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .extract().body().as(getBookTypeRef());
    assertEquals(NB_BOOKS + 1, books.size());
  }

  @Test
  @Order(3)
  void shouldUpdateAnItem() {
    Book book = new Book();
    book.id = Long.valueOf(bookId);
    book.title = UPDATED_TITLE;
    book.isbn = UPDATED_ISBN;
    book.author = UPDATED_AUTHOR;
    book.yearOfPublication = UPDATED_YEAR_OF_PUBLICATION;
    book.genre = UPDATED_GENRE;
    book.price = UPDATED_PRICE;

    given()
      .body(book)
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .header(ACCEPT, APPLICATION_JSON)
      .when()
      .put("/api/books")
      .then()
      .statusCode(OK.getStatusCode())
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .body("title", Is.is(UPDATED_TITLE))
      .body("isbn", Is.is(UPDATED_ISBN))
      .body("author", Is.is(UPDATED_AUTHOR))
      .body("yearOfPublication", Is.is(UPDATED_YEAR_OF_PUBLICATION))
      .body("genre", Is.is(UPDATED_GENRE))
      .body("price", Is.is(UPDATED_PRICE));


    List<Book> books = get("/api/books").then()
      .statusCode(OK.getStatusCode())
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .extract().body().as(getBookTypeRef());
    assertEquals(NB_BOOKS + 1, books.size());
  }

  @Test
  @Order(4)
  void shouldRemoveAnItem() {
    given()
      .pathParam("id", bookId)
      .when().delete("/api/books/{id}")
      .then()
      .statusCode(NO_CONTENT.getStatusCode());

    List<Book> books = get("/api/books").then()
      .statusCode(OK.getStatusCode())
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .extract().body().as(getBookTypeRef());
    assertEquals(NB_BOOKS, books.size());
  }

  private TypeRef<List<Book>> getBookTypeRef() {
    return new TypeRef<List<Book>>() {
      // Kept empty on purpose
    };
  }
}
// end::adocResourceTest[]
