package org.agoncal.fascicle.quarkus.book;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
// tag::adocImportLogger[]
import org.jboss.logging.Logger;
// end::adocImportLogger[]

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

// tag::adocInjection[]
// tag::adocSnippet[]
// tag::adocGetRandomBook[]
@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Book Endpoint")
public class BookResource {

  @Inject
  BookService service;
  // end::adocInjection[]

  private static final Logger LOGGER = Logger.getLogger(BookResource.class);

  @Operation(summary = "Returns a random book")
  @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Book.class)))
  // tag::adocMetrics[]
  @Counted(name = "countGetRandomBook", description = "Counts how many times the getRandomBook method has been invoked")
  @Timed(name = "timeGetRandomBook", description = "Times how long it takes to invoke the getRandomBook method", unit = MetricUnits.MILLISECONDS)
  // end::adocMetrics[]
  @GET
  @Path("/random")
  public Response getRandomBook() {
    Book book = service.findRandomBook();
    LOGGER.debug("Found random book " + book);
    return Response.ok(book).build();
  }
  // end::adocGetRandomBook[]

  // tag::adocGetAllBooks[]
  @Operation(summary = "Returns all the books from the database")
  @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Book.class, type = SchemaType.ARRAY)))
  @APIResponse(responseCode = "204", description = "No books")
  // tag::adocMetrics[]
  @Counted(name = "countGetAllBooks", description = "Counts how many times the getAllBooks method has been invoked")
  @Timed(name = "timeGetAllBooks", description = "Times how long it takes to invoke the getAllBooks method", unit = MetricUnits.MILLISECONDS)
  // end::adocMetrics[]
  @GET
  public Response getAllBooks() {
    List<Book> books = service.findAllBooks();
    LOGGER.debug("Total number of books " + books);
    return Response.ok(books).build();
  }
  // end::adocGetAllBooks[]

  // tag::adocGetBook[]
  @Operation(summary = "Returns a book for a given identifier")
  @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Book.class)))
  @APIResponse(responseCode = "404", description = "The book is not found for the given identifier")
  // tag::adocMetrics[]
  @Counted(name = "countGetBook", description = "Counts how many times the getBook method has been invoked")
  @Timed(name = "timeGetBook", description = "Times how long it takes to invoke the getBook method", unit = MetricUnits.MILLISECONDS)
  // end::adocMetrics[]
  @GET
  @Path("/{id}")
  public Response getBook(@Parameter(description = "Book identifier", required = true) @PathParam("id") Long id) {
    Optional<Book> book = service.findBookById(id);
    if (book.isPresent()) {
      LOGGER.debug("Found book " + book);
      return Response.ok(book).build();
    } else {
      LOGGER.debug("No book found with id " + id);
      return Response.status(NOT_FOUND).build();
    }
  }
  // end::adocGetBook[]

  // tag::adocCreateBook[]
  @Operation(summary = "Creates a valid book")
  @APIResponse(responseCode = "201", description = "The URI of the created book", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = URI.class)))
  // tag::adocMetrics[]
  @Counted(name = "countCreateBook", description = "Counts how many times the createBook method has been invoked")
  @Timed(name = "timeCreateBook", description = "Times how long it takes to invoke the createBook method", unit = MetricUnits.MILLISECONDS)
  // end::adocMetrics[]
  @POST
  public Response createBook(@RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Book.class))) @Valid Book book, @Context UriInfo uriInfo) {
    book = service.persistBook(book);
    UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(book.id));
    LOGGER.debug("New book created with URI " + builder.build().toString());
    return Response.created(builder.build()).build();
  }

  @Operation(summary = "Updates an existing  book")
  @APIResponse(responseCode = "200", description = "The updated book", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Book.class)))
  // tag::adocMetrics[]
  @Counted(name = "countUpdateBook", description = "Counts how many times the updateBook method has been invoked")
  @Timed(name = "timeUpdateBook", description = "Times how long it takes to invoke the updateBook method", unit = MetricUnits.MILLISECONDS)
  // end::adocMetrics[]
  @PUT
  public Response updateBook(@RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Book.class))) @Valid Book book) {
    book = service.updateBook(book);
    LOGGER.debug("Book updated with new valued " + book);
    return Response.ok(book).build();
  }
  // end::adocCreateBook[]

  // tag::adocDeleteBook[]
  @Operation(summary = "Deletes an existing book")
  @APIResponse(responseCode = "204", description = "The book has been successfully deleted")
  // tag::adocMetrics[]
  @Counted(name = "countDeleteBook", description = "Counts how many times the deleteBook method has been invoked")
  @Timed(name = "timeDeleteBook", description = "Times how long it takes to invoke the deleteBook method", unit = MetricUnits.MILLISECONDS)
  // end::adocMetrics[]
  @DELETE
  @Path("/{id}")
  public Response deleteBook(@Parameter(description = "Book identifier", required = true) @PathParam("id") Long id) {
    service.deleteBook(id);
    LOGGER.debug("Book deleted with " + id);
    return Response.noContent().build();
  }
  // end::adocDeleteBook[]

  // tag::adocPing[]
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/ping")
  public String ping() {
    return "ping";
  }
  // end::adocPing[]
}
// end::adocSnippet[]
