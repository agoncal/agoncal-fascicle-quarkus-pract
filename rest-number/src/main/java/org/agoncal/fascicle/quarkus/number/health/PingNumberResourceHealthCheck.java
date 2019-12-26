// tag::adocPingHero[]
package org.agoncal.fascicle.quarkus.number.health;

import org.agoncal.fascicle.quarkus.number.NumberResource;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Liveness
@ApplicationScoped
public class PingNumberResourceHealthCheck implements HealthCheck {

  @Inject
  NumberResource numberResource;

  @Override
  public HealthCheckResponse call() {
    numberResource.ping();
    return HealthCheckResponse.named("Ping Number REST Endpoint").up().build();
  }
}
// end::adocPingHero[]
