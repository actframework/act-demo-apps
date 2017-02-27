package demo.event;

public class InstantByeEventProcessor extends ByeEventProcessor {

    public InstantByeEventProcessor() {
        logger.info("constructing instant bye event processor...");
    }

    @Override
    public void on(ByeEvent event) {
        logger.info("[Instant]%s, bye bye!", event.source().who());
    }
}
