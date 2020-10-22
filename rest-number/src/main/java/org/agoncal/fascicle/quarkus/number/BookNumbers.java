package org.agoncal.fascicle.quarkus.number;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

// tag::adocImportJSonB[]
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
// end::adocImportJSonB[]
import java.time.Instant;

// tag::adocSnippet[]
// tag::adocOpenAPI[]
@Schema(description = "Several formats of book numbers")
// end::adocOpenAPI[]
public class BookNumbers {

  // tag::adocOpenAPI[]
  @Schema(required = true)
  // end::adocOpenAPI[]
  // tag::adocJSonB[]
  @JsonbProperty("isbn_10")
  // end::adocJSonB[]
  public String isbn10;
  // tag::adocOpenAPI[]
  @Schema(required = true)
  // end::adocOpenAPI[]
  // tag::adocJSonB[]
  @JsonbProperty("isbn_13")
  // end::adocJSonB[]
  public String isbn13;
  public String asin;
  // tag::adocJSonB[]
  @JsonbProperty("ean_8")
  // end::adocJSonB[]
  public String ean8;
  // tag::adocJSonB[]
  @JsonbProperty("ean_13")
  // end::adocJSonB[]
  public String ean13;
  // tag::adocJSonB[]
  @JsonbTransient
  // end::adocJSonB[]
  public Instant generationDate;
}
// end::adocSnippet[]
