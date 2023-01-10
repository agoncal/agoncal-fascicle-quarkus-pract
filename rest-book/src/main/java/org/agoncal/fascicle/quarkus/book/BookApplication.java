package org.agoncal.fascicle.quarkus.book;

import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

// tag::adocSnippet[]
@ApplicationPath("/")
@OpenAPIDefinition(
  info = @Info(title = "Book API",
    description = "This API allows CRUD operations on books",
    version = "2.0",
    contact = @Contact(name = "@agoncal", url = "https://twitter.com/agoncal")),
  externalDocs = @ExternalDocumentation(url = "https://github.com/agoncal/agoncal-fascicle-quarkus-pract", description = "All the Practising Quarkus code"),
  tags = {
    @Tag(name = "api", description = "Public API that can be used by anybody"),
    @Tag(name = "books", description = "Anybody interested in books")
  }
)
public class BookApplication extends Application {
}
// end::adocSnippet[]
