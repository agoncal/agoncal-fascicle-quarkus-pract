package org.agoncal.fascicle.quarkus.book;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.CoreMatchers.is;

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
}
