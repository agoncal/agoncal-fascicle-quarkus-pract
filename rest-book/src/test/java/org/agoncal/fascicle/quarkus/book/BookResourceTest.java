package org.agoncal.fascicle.quarkus.book;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class BookResourceTest {

  @Test
  public void shouldSayPing() {
    given()
      .when().get("/books/ping")
      .then()
      .statusCode(200)
      .body(is("ping"));
  }

  @Test
  public void shouldnotFindDummy() {
    given()
      .when().get("/books/dummy")
      .then()
      .statusCode(404);
  }
}
