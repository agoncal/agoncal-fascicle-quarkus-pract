package org.agoncal.fascicle.quarkus.number;

import io.quarkus.test.junit.DisabledOnNativeImage;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

// tag::adocImports[]
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;
// end::adocImports[]

//@formatter:off
// tag::adocSnippet[]
@QuarkusTest
public class NumberResourceTest {

  @Test
  void shouldGenerateBookNumber() {
    given()
      .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).
    when()
      .get("/api/numbers").
    then()
      .statusCode(OK.getStatusCode())
      .body("$", hasKey("isbn_10"))
      .body("$", hasKey("isbn_13"))
      .body("$", hasKey("asin"))
      .body("$", hasKey("ean_8"))
      .body("$", hasKey("ean_13"))
      .body("$", not(hasKey("generationDate")));
  }
  // tag::adocPing[]
  @Test
  void shouldSayPing() {
    given()
      .header(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN).
    when()
      .get("/api/numbers/ping").
    then()
      .statusCode(OK.getStatusCode())
      .body(is("hello"));
  }
  // end::adocPing[]
  // tag::adocOpenAPI[]
  @Test
  void shouldPingOpenAPI() {
    given()
      .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).
    when()
      .get("/q/openapi").
    then()
      .statusCode(OK.getStatusCode());
  }

  // tag::adocNative[]
  @DisabledOnNativeImage
  // end::adocNative[]
  @Test
  void shouldPingSwaggerUI() {
    given().
    when()
      .get("/q/swagger-ui").
    then()
      .statusCode(OK.getStatusCode());
  }
  // end::adocOpenAPI[]
  // tag::adocHealth[]
  @Test
  void shouldPingLiveness() {
    given()
      .header(ACCEPT, APPLICATION_JSON).
    when()
      .get("/q/health/live").
    then()
      .statusCode(OK.getStatusCode());
  }

  @Test
  void shouldPingReadiness() {
    given()
      .header(ACCEPT, APPLICATION_JSON).
    when()
      .get("/q/health/ready").
    then()
      .statusCode(OK.getStatusCode());
  }
  // end::adocHealth[]
  // tag::adocMetrics[]
  @Test
  void shouldPingMetrics() {
    given()
      .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).
    when()
      .get("/q/metrics/application").
    then()
      .statusCode(OK.getStatusCode());
  }
  // end::adocMetrics[]
  // tag::adocSkip[]
  @Test
  void shouldNotFindDummy() {
    given()
      .header(ACCEPT, APPLICATION_JSON).
    when()
      .get("/api/numbers/dummy").
    then()
      .statusCode(NOT_FOUND.getStatusCode());
  }
  // end::adocSkip[]
}
// end::adocSnippet[]
