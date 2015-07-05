package act.fsa.event;

import act.util.Async;

@Async
public class EmailWelcomeEventProcessor extends WelcomeEventProcessor {
    @Override
    public void on(WelcomeEvent event) {
        logger.info("[email]%s welcome!", event.source().who());
    }
}
