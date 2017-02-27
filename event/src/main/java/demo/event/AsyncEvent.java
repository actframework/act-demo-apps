package demo.event;

import act.event.ActEvent;
import act.event.OnEvent;
import act.util.Async;
import org.osgl.logging.LogManager;
import org.osgl.logging.Logger;

@Async
public class AsyncEvent extends ActEvent<String> {

    private static final Logger LOGGER = LogManager.get(AsyncEvent.class);

    public AsyncEvent(String name) {
        super(name);
    }

    @OnEvent
    public static void handle(AsyncEvent event) {
        LOGGER.info("async event: " + event.source());
    }

}
