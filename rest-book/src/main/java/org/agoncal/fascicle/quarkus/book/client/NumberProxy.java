package org.agoncal.fascicle.quarkus.book.client;

// tag::adocImports[]
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
// end::adocImports[]

// tag::adocSnippet[]
@Path("/api/numbers")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient
public interface NumberProxy {

  @GET
  IsbnNumbers generateNumbers();
}
// end::adocSnippet[]
