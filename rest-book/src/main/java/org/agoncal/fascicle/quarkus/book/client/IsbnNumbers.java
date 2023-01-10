package org.agoncal.fascicle.quarkus.book.client;

import jakarta.json.bind.annotation.JsonbProperty;

// tag::adocSnippet[]
public class IsbnNumbers {

  @JsonbProperty("isbn_10")
  public String isbn10;
  @JsonbProperty("isbn_13")
  public String isbn13;
}
// end::adocSnippet[]
