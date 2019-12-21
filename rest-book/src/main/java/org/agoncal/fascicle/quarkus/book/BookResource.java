package org.agoncal.fascicle.quarkus.book;

import org.jboss.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/books")
@Produces(APPLICATION_JSON)
public class BookResource {

  private static final Logger LOGGER = Logger.getLogger(BookResource.class);

  @GET
  @Produces(TEXT_PLAIN)
  @Path("/ping")
  public String ping() {
    return "ping";
  }
}
