package org.agoncal.fascicle.quarkus.number.health;

import org.agoncal.fascicle.quarkus.number.BookNumbersResource;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

// tag::adocSnippet[]
@Liveness
@ApplicationScoped
public class PingBookNumbersResourceHealthCheck implements HealthCheck {

  @Inject
  BookNumbersResource numberResource;

  @Override
  public HealthCheckResponse call() {
    numberResource.ping();
    return HealthCheckResponse.named("Ping Number REST Endpoint").up().build();
  }
}
// end::adocSnippet[]
