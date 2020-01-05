// tag::adocTest[]
package org.agoncal.fascicle.quarkus.book.client;

import io.quarkus.test.Mock;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;

// tag::adocSnippet[]
@Mock
@ApplicationScoped
@RestClient
public class MockIsbnNumbersService implements IsbnNumbersService {

  public static final String MOCK_ISBN_13 = "Isbn 13";
  public static final String MOCK_ISBN_10 = "Isbn 10";

  @Override
  public IsbnNumbers generateIsbnNumbers() {
    IsbnNumbers isbnNumbers = new IsbnNumbers();
    isbnNumbers.setIsbn13(MOCK_ISBN_13);
    isbnNumbers.setIsbn10(MOCK_ISBN_10);
    return isbnNumbers;
  }
}
// end::adocSnippet[]
