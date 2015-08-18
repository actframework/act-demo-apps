package act.fsa.email;

import javax.inject.Inject;
import javax.inject.Singleton;

public class EmailWelcomeEventProcessor extends WelcomeEventProcessor {

    @Inject
    @Singleton
    private PostOffice postOffice;

    @Override
    public void on(WelcomeEvent event) {
        postOffice.sendWelcome(event.source().who());
    }
}
