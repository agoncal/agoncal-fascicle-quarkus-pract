package org.agoncal.fascicle.quarkus.number;

import com.github.javafaker.Faker;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

// tag::adocSnippet[]
@Path("/api/numbers")
public class NumberResource {

  private static final Logger LOGGER = Logger.getLogger(NumberResource.class);

  // tag::adocConfigProperty[]
  @ConfigProperty(name = "seconds.sleep", defaultValue = "0")
  int sleep = 0;

  // end::adocConfigProperty[]
  // tag::adocOpenAPI[]
  @Operation(summary = "Generates a book number.")
  @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.TEXT_PLAIN, schema = @Schema(implementation = String.class, required = true)))
  // end::adocOpenAPI[]
  // tag::adocMetrics[]
  @Counted(name = "countGenerateBookNumber", description = "Counts how many times the generateBookNumber method has been invoked")
  @Timed(name = "timeGenerateBookNumber", description = "Times how long it takes to invoke the generateBookNumber method", unit = MetricUnits.MILLISECONDS)
  // end::adocMetrics[]
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response generateBookNumber() throws InterruptedException {
    // tag::adocConfigProperty[]
    LOGGER.info("Waiting for " + sleep + " seconds");
    TimeUnit.SECONDS.sleep(sleep);
    // end::adocConfigProperty[]
    LOGGER.info("Generating book numbers");
    Faker faker = new Faker();
    BookNumbers bookNumbers = new BookNumbers();
    bookNumbers.setIsbn10(faker.code().isbn10());
    bookNumbers.setIsbn13(faker.code().isbn13());
    bookNumbers.setAsin(faker.code().asin());
    bookNumbers.setEan8(faker.code().ean8());
    bookNumbers.setEan13(faker.code().ean13());
    bookNumbers.setGenerationDate(Instant.now());
    return Response.ok(bookNumbers).build();
  }
  // tag::adocPing[]

  @GET
  @Path("/ping")
  @Produces(MediaType.TEXT_PLAIN)
  public String ping() {
    return "ping";
  }
  // end::adocPing[]
}
// end::adocSnippet[]
