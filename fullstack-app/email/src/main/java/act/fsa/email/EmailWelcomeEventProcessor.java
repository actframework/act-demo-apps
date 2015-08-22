package act.fsa.email;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.constraints.NotNull;

public class EmailWelcomeEventProcessor extends WelcomeEventProcessor {

    private PostOffice postOffice;

    @Inject
    @Singleton
    public EmailWelcomeEventProcessor(@NotNull PostOffice postOffice) {
        this.postOffice = postOffice;
    }

    @Override
    public void on(WelcomeEvent event) {
        postOffice.sendWelcome(event.source().who());
    }
}
