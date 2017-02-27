package demo.event;

import act.event.OnEvent;
import act.util.Async;
import org.osgl.logging.LogManager;
import org.osgl.logging.Logger;

@Async
public class AsyncEvent {

    private static final Logger LOGGER = LogManager.get(AsyncEvent.class);

    private String name;

    public AsyncEvent(String name) {
        this.name = name;
    }

    @OnEvent
    public static void handleEvent(AsyncEvent event) {
        LOGGER.info("async event: " + event.name);
    }

}
