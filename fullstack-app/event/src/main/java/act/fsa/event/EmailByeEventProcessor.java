package act.fsa.event;

import act.util.Async;

@Async
public class EmailByeEventProcessor extends ByeEventProcessor {
    @Override
    public void on(ByeEvent event) {
        logger.info("[email]%s bye bye!", event.source().who());
    }
}
