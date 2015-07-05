package act.fsa.event;

public class InstantByeEventProcessor extends ByeEventProcessor {
    @Override
    public void on(ByeEvent event) {
        logger.info("[Instant]%s, bye bye!", event.source().who());
    }
}
