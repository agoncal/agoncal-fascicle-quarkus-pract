package org.agoncal.fascicle.quarkus.number;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;

@QuarkusTest
public class NumberResourceTest {

  @Test
  public void shouldGenerateBookNumber() {
    given()
      .when().get("/numbers/book")
      .then()
      .statusCode(200)
      .body(startsWith("BK-"));
  }

  @Test
  public void shouldSayPing() {
    given()
      .when().get("/numbers/ping")
      .then()
      .statusCode(200)
      .body(is("ping"));
  }

  @Test
  public void shouldNotFindDummy() {
    given()
      .when().get("/numbers/dummy")
      .then()
      .statusCode(404);
  }
}
