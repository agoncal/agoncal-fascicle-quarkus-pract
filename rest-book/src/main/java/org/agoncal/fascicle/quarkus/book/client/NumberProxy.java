package org.agoncal.fascicle.quarkus.book.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// tag::adocSnippet[]
@Path("/api/numbers/book")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient
public interface NumberProxy {

  @GET
  IsbnNumbers generateIsbnNumbers();
}
// end::adocSnippet[]
