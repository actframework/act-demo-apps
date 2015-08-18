package act.fsa.email;

import javax.inject.Inject;

public class EmailByeEventProcessor extends ByeEventProcessor {

    @Inject
    PostOffice postOffice;

    @Override
    public void on(ByeEvent event) {
        postOffice.sendBye(event.source().who());
    }
}
