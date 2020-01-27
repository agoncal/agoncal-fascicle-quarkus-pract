package org.agoncal.fascicle.quarkus.book;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import org.agoncal.fascicle.quarkus.book.infrastructure.Database;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
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
import static org.agoncal.fascicle.quarkus.book.client.MockIsbnNumbersService.MOCK_ISBN_10;
import static org.agoncal.fascicle.quarkus.book.client.MockIsbnNumbersService.MOCK_ISBN_13;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

// tag::adocHeader[]
@QuarkusTest
@QuarkusTestResource(Database.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookResourceTest {

  private static final String DEFAULT_TITLE = "Title";
  private static final String UPDATED_TITLE = "Title (updated)";
  private static final String DEFAULT_AUTHOR = "Author";
  private static final String UPDATED_AUTHOR = "Author (updated)";
  private static final Integer DEFAULT_YEAR_OF_PUBLICATION = 1111;
  private static final Integer UPDATED_YEAR_OF_PUBLICATION = 2222;
  private static final Integer DEFAULT_NB_OF_PAGES = 111;
  private static final Integer UPDATED_NB_OF_PAGES = 222;
  private static final Integer DEFAULT_RANK = 1;
  private static final Integer UPDATED_RANK = 2;
  private static final BigDecimal DEFAULT_PRICE = new BigDecimal(11.0);
  private static final BigDecimal UPDATED_PRICE = new BigDecimal(22.0);
  private static final URL DEFAULT_SMALL_IMAGE_URL = makeUrl("http://www.url.com");
  private static final URL UPDATED_SMALL_IMAGE_URL = makeUrl("http://www.updatedurl.com");
  private static final URL DEFAULT_MEDIUM_IMAGE_URL = makeUrl("http://www.url.com");
  private static final URL UPDATED_MEDIUM_IMAGE_URL = makeUrl("http://www.updatedurl.com");
  private static final String DEFAULT_DESCRIPTION = "Description";
  private static final String UPDATED_DESCRIPTION = "Description (updated)";

  private static URL makeUrl(String urlString) {
    try {
      return new URL(urlString);
    } catch (MalformedURLException e) {
      return null;
    }
  }

  private static int nbBooks;
  private static String bookId;

// end::adocHeader[]

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

  // tag::adocShouldNotAddInvalidItem[]
  @Test
  void shouldNotAddInvalidItem() {
    Book book = new Book();
    book.title = null;

    given()
      .body(book)
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .header(ACCEPT, APPLICATION_JSON)
      .when()
      .post("/api/books")
      .then()
      .statusCode(BAD_REQUEST.getStatusCode());
  }
  // end::adocShouldNotAddInvalidItem[]

  // tag::adocShouldGetInitialItems[]
  @Test
  @Order(1)
  void shouldGetInitialItems() {
    List<Book> books = get("/api/books").then()
      .statusCode(OK.getStatusCode())
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
      .extract().body().as(getBookTypeRef());

    nbBooks = books.size();
  }

  private TypeRef<List<Book>> getBookTypeRef() {
    return new TypeRef<List<Book>>() {};
  }
  // end::adocShouldGetInitialItems[]

  // tag::adocShouldAddAnItem[]
  @Test
  @Order(2)
  void shouldAddAnItem() {
    Book book = new Book();
    book.title = DEFAULT_TITLE;
    book.author = DEFAULT_AUTHOR;
    book.yearOfPublication = DEFAULT_YEAR_OF_PUBLICATION;
    book.nbOfPages = DEFAULT_NB_OF_PAGES;
    book.rank = DEFAULT_RANK;
    book.price = DEFAULT_PRICE;
    book.smallImageUrl = DEFAULT_SMALL_IMAGE_URL;
    book.mediumImageUrl = DEFAULT_MEDIUM_IMAGE_URL;
    book.description = DEFAULT_DESCRIPTION;

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
      .body("isbn13", Is.is(MOCK_ISBN_13))
      .body("isbn10", Is.is(MOCK_ISBN_10))
      .body("author", Is.is(DEFAULT_AUTHOR))
      .body("yearOfPublication", Is.is(DEFAULT_YEAR_OF_PUBLICATION))
      .body("nbOfPages", Is.is(DEFAULT_NB_OF_PAGES))
      .body("rank", Is.is(DEFAULT_RANK))
      .body("smallImageUrl", Is.is(DEFAULT_SMALL_IMAGE_URL.toString()))
      .body("mediumImageUrl", Is.is(DEFAULT_MEDIUM_IMAGE_URL.toString()))
      .body("description", Is.is(DEFAULT_DESCRIPTION));

    List<Book> books = get("/api/books").then()
      .statusCode(OK.getStatusCode())
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .extract().body().as(getBookTypeRef());

    assertEquals(nbBooks + 1, books.size());
  }
  // end::adocShouldAddAnItem[]

  // tag::adocShouldUpdateAnItem[]
  @Test
  @Order(3)
  void shouldUpdateAnItem() {
    Book book = new Book();
    book.id = Long.valueOf(bookId);
    book.title = UPDATED_TITLE;
    book.isbn13 = MOCK_ISBN_13;
    book.isbn10 = MOCK_ISBN_10;
    book.author = UPDATED_AUTHOR;
    book.yearOfPublication = UPDATED_YEAR_OF_PUBLICATION;
    book.nbOfPages = UPDATED_NB_OF_PAGES;
    book.rank = UPDATED_RANK;
    book.price = UPDATED_PRICE;
    book.smallImageUrl = UPDATED_SMALL_IMAGE_URL;
    book.mediumImageUrl = UPDATED_MEDIUM_IMAGE_URL;
    book.description = UPDATED_DESCRIPTION;

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
      .body("isbn13", Is.is(MOCK_ISBN_13))
      .body("isbn10", Is.is(MOCK_ISBN_10))
      .body("author", Is.is(UPDATED_AUTHOR))
      .body("yearOfPublication", Is.is(UPDATED_YEAR_OF_PUBLICATION))
      .body("nbOfPages", Is.is(UPDATED_NB_OF_PAGES))
      .body("rank", Is.is(UPDATED_RANK))
      .body("price", Is.is(UPDATED_PRICE.intValue()))
      .body("smallImageUrl", Is.is(UPDATED_SMALL_IMAGE_URL.toString()))
      .body("mediumImageUrl", Is.is(UPDATED_MEDIUM_IMAGE_URL.toString()))
      .body("description", Is.is(UPDATED_DESCRIPTION));


    List<Book> books = get("/api/books").then()
      .statusCode(OK.getStatusCode())
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .extract().body().as(getBookTypeRef());

    assertEquals(nbBooks + 1, books.size());
  }
  // end::adocShouldUpdateAnItem[]

  // tag::adocShouldRemoveAnItem[]
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

    assertEquals(nbBooks, books.size());
  }
  // end::adocShouldRemoveAnItem[]
}
