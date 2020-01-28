// tag::adocTest[]
package org.agoncal.fascicle.quarkus.book.client;

import io.quarkus.test.Mock;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;

import static org.agoncal.fascicle.quarkus.book.BookResourceTest.MOCK_ISBN_10;
import static org.agoncal.fascicle.quarkus.book.BookResourceTest.MOCK_ISBN_13;

// tag::adocSnippet[]
@Mock
@ApplicationScoped
@RestClient
public class MockIsbnNumbersService implements IsbnNumbersService {

  @Override
  public IsbnNumbers generateIsbnNumbers() {
    IsbnNumbers isbnNumbers = new IsbnNumbers();
    isbnNumbers.setIsbn13(MOCK_ISBN_13);
    isbnNumbers.setIsbn10(MOCK_ISBN_10);
    return isbnNumbers;
  }
}
// end::adocSnippet[]
