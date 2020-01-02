package org.agoncal.fascicle.quarkus.number;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;

// tag::adocSnippet[]
@QuarkusTest
public class NumberResourceTest {

  @Test
  public void shouldGenerateBookNumber() {
    given()
      .header(ACCEPT, APPLICATION_JSON)
      .when().get("/api/numbers")
      .then()
      .statusCode(200)
      .body("$", hasKey("isbn-10"))
      .body("$", hasKey("isbn-13"))
      .body("$", hasKey("asin"))
      .body("$", hasKey("ean-8"))
      .body("$", hasKey("ean-13"))
      .body("$", not(hasKey("generationDate")));
  }

  // tag::adocPing[]
  @Test
  public void shouldSayPing() {
    given()
      .header(ACCEPT, TEXT_PLAIN)
      .when().get("/api/numbers/ping")
      .then()
      .statusCode(200)
      .body(is("ping"));
  }

  // end::adocPing[]
  // tag::adocOpenAPI[]
  @Test
  void shouldPingOpenAPI() {
    given()
      .header(ACCEPT, APPLICATION_JSON)
      .when().get("/openapi")
      .then()
      .statusCode(OK.getStatusCode());
  }

  // end::adocOpenAPI[]
  // tag::adocSwagger[]
  @Test
  void shouldPingSwaggerUI() {
    given()
      .when().get("/swagger-ui")
      .then()
      .statusCode(OK.getStatusCode());
  }

  // end::adocSwagger[]
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
      .when().get("/api/numbers/dummy")
      .then()
      .statusCode(404);
  }
}
// end::adocSnippet[]
