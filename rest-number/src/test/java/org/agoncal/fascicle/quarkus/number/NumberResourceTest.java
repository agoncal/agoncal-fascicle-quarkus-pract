package org.agoncal.fascicle.quarkus.number;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;

@QuarkusTest
public class NumberResourceTest {

  @Test
  public void shouldGenerateBookNumber() {
    given()
      .when().get("/api/numbers/book")
      .then()
      .statusCode(200)
      .body(startsWith("BK-"));
  }

  @Test
  public void shouldSayPing() {
    given()
      .when().get("/api/numbers/ping")
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
      .when().get("/api/numbers/dummy")
      .then()
      .statusCode(404);
  }
}
