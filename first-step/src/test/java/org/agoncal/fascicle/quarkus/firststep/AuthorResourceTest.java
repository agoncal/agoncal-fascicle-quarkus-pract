package org.agoncal.fascicle.quarkus.firststep;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
//@formatter:off
// tag::adocSnippet[]
@QuarkusTest
public class AuthorResourceTest {

  @Test
  public void shouldGetAllAuthors() {
    given().
    when()
      .get("/authors").
    then()
      .statusCode(200)
      .body(is("Isaac Asimov, Ray Bradbury, Douglas Adams"));
  }

  @Test
  public void shouldGetAnAuthor() {
    given()
      .pathParam("index", 0).
    when()
      .get("/authors/{index}").
    then()
      .statusCode(200)
      .body(is("Isaac Asimov"));
  }
}
// end::adocSnippet[]
