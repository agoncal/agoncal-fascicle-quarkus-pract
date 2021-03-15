package org.agoncal.fascicle.quarkus.number;
//@formatter:off

// tag::adocImports[]
import org.jboss.logging.Logger;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;import javax.inject.Inject;
// end::adocImports[]

// tag::adocSnippet[]
@ApplicationScoped
class NumberApplicationLifeCycle {

  @Inject
  Logger LOGGER;

  void onStart(@Observes StartupEvent ev) {
    LOGGER.info(" _   _                 _               ");
    LOGGER.info("| \\ | |               | |              ");
    LOGGER.info("|  \\| |_   _ _ __ ___ | |__   ___ _ __");
    LOGGER.info("| . ` | | | | '_ ` _ \\| '_ \\ / _ \\ '__|");
    LOGGER.info("| |\\  | |_| | | | | | | |_) |  __/ |   ");
    LOGGER.info("\\_| \\_/\\__,_|_| |_| |_|_.__/ \\___|_|   ");
    LOGGER.info("                         Powered by Quarkus");
    // tag::adocProfile[]
    LOGGER.info("The application Number is starting with profile " + ProfileManager.getActiveProfile());
    // end::adocProfile[]
  }

  void onStop(@Observes ShutdownEvent ev) {
    LOGGER.info("The application Number is stopping...");
  }
}
// end::adocSnippet[]
