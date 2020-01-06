package org.agoncal.fascicle.quarkus.book;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

// tag::adocSnippet[]
@ApplicationScoped
class BookApplicationLifeCycle {

    private static final Logger LOGGER = Logger.getLogger(BookApplicationLifeCycle.class);

    // tag::adocStartupEvent[]
    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("______             _");
        LOGGER.info("| ___ \\           | |");
        LOGGER.info("| |_/ / ___   ___ | | __");
        LOGGER.info("| ___ \\/ _ \\ / _ \\| |/ /");
        LOGGER.info("| |_/ / (_) | (_) |   <");
        LOGGER.info("\\____/ \\___/ \\___/|_|\\_\\");
        LOGGER.info("                         Powered by Quarkus");
        // tag::adocProfileManager[]
        LOGGER.info("The application Book is starting with profile " + ProfileManager.getActiveProfile());
        // end::adocProfileManager[]
    }
    // end::adocStartupEvent[]

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("The application HERO is stopping...");
    }
}
// end::adocSnippet[]
