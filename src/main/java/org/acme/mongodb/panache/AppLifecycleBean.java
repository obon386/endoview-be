package org.acme.mongodb.panache;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class AppLifecycleBean {

    private static final Logger LOGGER = LoggerFactory.getLogger("ListenerBean");

    /**
     * Inject a bean used in the callbacks.
     */
    @Inject
    PictureMap pictureMap;

    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("The application is starting...");

//        pictureMap.init();
    }


}
