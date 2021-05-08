package org.agoncal.fascicle.quarkus.number.health;

import org.agoncal.fascicle.quarkus.number.NumberResource;
// tag::adocImports[]
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
// end::adocImports[]

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

// tag::adocSnippet[]
@Liveness
@ApplicationScoped
public class PingNumberResourceHealthCheck implements HealthCheck {

  @Inject
  NumberResource numberResource;

  @Override
  public HealthCheckResponse call() {
    numberResource.hello();
    return HealthCheckResponse.named("Ping Number REST Endpoint").up().build();
  }
}
// end::adocSnippet[]
