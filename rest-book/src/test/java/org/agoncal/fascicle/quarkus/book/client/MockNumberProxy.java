// tag::adocTest[]
package org.agoncal.fascicle.quarkus.book.client;

import io.quarkus.test.Mock;
import org.agoncal.fascicle.quarkus.book.BookResourceTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;

// tag::adocSnippet[]
@Mock
@ApplicationScoped
@RestClient
public class MockNumberProxy implements NumberProxy {

  @Override
  public IsbnNumbers generateIsbnNumbers() {
    IsbnNumbers isbnNumbers = new IsbnNumbers();
    isbnNumbers.setIsbn13(BookResourceTest.MOCK_ISBN_13);
    isbnNumbers.setIsbn10(BookResourceTest.MOCK_ISBN_10);
    return isbnNumbers;
  }
}
// end::adocSnippet[]
