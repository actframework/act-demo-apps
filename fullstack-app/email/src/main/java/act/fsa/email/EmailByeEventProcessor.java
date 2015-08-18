package act.fsa.email;

import javax.inject.Inject;
import javax.inject.Singleton;

public class EmailByeEventProcessor extends ByeEventProcessor {

    @Inject
    @Singleton
    PostOffice postOffice;

    @Override
    public void on(ByeEvent event) {
        postOffice.sendBye(event.source().who());
    }
}
