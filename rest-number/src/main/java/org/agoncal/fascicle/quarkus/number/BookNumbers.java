package org.agoncal.fascicle.quarkus.number;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
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
  @JsonbProperty("isbn-10")
  // end::adocJSonB[]
  private String isbn10;
  // tag::adocOpenAPI[]
  @Schema(required = true)
  // end::adocOpenAPI[]
  // tag::adocJSonB[]
  @JsonbProperty("isbn-13")
  // end::adocJSonB[]
  private String isbn13;
  private String asin;
  // tag::adocJSonB[]
  @JsonbProperty("ean-8")
  // end::adocJSonB[]
  private String ean8;
  // tag::adocJSonB[]
  @JsonbProperty("ean-13")
  // end::adocJSonB[]
  private String ean13;
  // tag::adocJSonB[]
  @JsonbTransient
  // end::adocJSonB[]
  private Instant generationDate;

  // Constructors, getters, setters
  // tag::adocSkip[]
  public String getIsbn10() {
    return isbn10;
  }

  public void setIsbn10(String isbn10) {
    this.isbn10 = isbn10;
  }

  public String getIsbn13() {
    return isbn13;
  }

  public void setIsbn13(String isbn13) {
    this.isbn13 = isbn13;
  }

  public String getAsin() {
    return asin;
  }

  public void setAsin(String asin) {
    this.asin = asin;
  }

  public String getEan8() {
    return ean8;
  }

  public void setEan8(String ean8) {
    this.ean8 = ean8;
  }

  public String getEan13() {
    return ean13;
  }

  public void setEan13(String ean13) {
    this.ean13 = ean13;
  }

  public Instant getGenerationDate() {
    return generationDate;
  }

  public void setGenerationDate(Instant generationDate) {
    this.generationDate = generationDate;
  }
  // end::adocSkip[]
}
// end::adocSnippet[]
