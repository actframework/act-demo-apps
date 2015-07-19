package act.fsa.email;

public class EmailByeEventProcessor extends ByeEventProcessor {
    @Override
    public void on(ByeEvent event) {
        new PostOffice().sendBye(event.source().who());
    }
}
