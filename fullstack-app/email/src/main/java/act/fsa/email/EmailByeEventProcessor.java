package act.fsa.email;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.constraints.NotNull;

public class EmailByeEventProcessor extends ByeEventProcessor {

    PostOffice postOffice;

    @Inject
    @Singleton
    public EmailByeEventProcessor(@NotNull PostOffice postOffice) {
        this.postOffice = postOffice;
    }

    @Override
    public void on(ByeEvent event) {
        postOffice.sendBye(event.source().who());
    }
}
