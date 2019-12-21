package org.agoncal.fascicle.quarkus.number;

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
import javax.ws.rs.core.Response;
import java.util.concurrent.TimeUnit;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/numbers")
@Produces(TEXT_PLAIN)
public class NumberResource {

  private static final Logger LOGGER = Logger.getLogger(NumberResource.class);

  // tag::adocConfigProperty[]
  @ConfigProperty(name = "seconds.sleep", defaultValue = "0")
  int sleep = 0;

  // end::adocConfigProperty[]
  // tag::adocMetricsMethods[]
  // tag::adocOpenAPI[]
  @Operation(summary = "Generates a book number.")
  @APIResponse(responseCode = "200", content = @Content(mediaType = TEXT_PLAIN, schema = @Schema(implementation = String.class, required = true)))
  // end::adocOpenAPI[]
  // tag::adocMetrics[]
  @Counted(name = "countGenerateBookNumber", description = "Counts how many times the generateBookNumber method has been invoked")
  @Timed(name = "timeGenerateBookNumber", description = "Times how long it takes to invoke the generateBookNumber method", unit = MetricUnits.MILLISECONDS)
  // end::adocMetrics[]
  @GET
  @Path("book")
  public Response generateBookNumber() throws InterruptedException {
    // tag::adocConfigProperty[]
    LOGGER.info("Waiting for " + sleep + " seconds");
    TimeUnit.SECONDS.sleep(sleep);
    // end::adocConfigProperty[]
    LOGGER.info("Generating a book number");
    return Response.ok("BK-" + Math.random()).build();
  }

  @GET
  @Produces(TEXT_PLAIN)
  @Path("/ping")
  public String ping() {
    return "ping";
  }
}
