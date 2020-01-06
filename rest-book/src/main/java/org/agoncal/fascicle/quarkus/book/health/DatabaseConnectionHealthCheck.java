package org.agoncal.fascicle.quarkus.book.health;

import org.agoncal.fascicle.quarkus.book.Book;
import org.agoncal.fascicle.quarkus.book.BookService;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

// tag::adocSnippet[]
@Readiness
@ApplicationScoped
public class DatabaseConnectionHealthCheck implements HealthCheck {

    @Inject
    BookService bookService;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse
            .named("Book Datasource connection health check");

        try {
            List<Book> books = bookService.findAllBooks();
            responseBuilder.withData("Number of books in the database", books.size()).up();
        } catch (IllegalStateException e) {
            responseBuilder.down();
        }

        return responseBuilder.build();
    }
}
// end::adocSnippet[]
