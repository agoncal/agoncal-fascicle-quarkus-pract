// tag::adocApplication[]
package org.agoncal.fascicle.quarkus.number;

import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
@OpenAPIDefinition(
  info = @Info(title = "Number API",
    description = "This API allows to generate all sorts of numbers",
    version = "1.0",
    contact = @Contact(name = "@agoncal", url = "https://twitter.com/agoncal")),
  servers = {
    @Server(url = "http://localhost:8083")
  },
  externalDocs = @ExternalDocumentation(url = "https://github.com/agoncal/agoncal-fascicle-quarkus-pract", description = "All the Practicing Quarkus code"),
  tags = {
    @Tag(name = "api", description = "Public API that can be used by anybody"),
    @Tag(name = "numbers", description = "Anybody interested in numbers")
  }
)
public class NumberApplication extends Application {
}
// end::adocApplication[]
