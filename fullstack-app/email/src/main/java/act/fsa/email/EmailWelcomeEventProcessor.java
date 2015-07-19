package act.fsa.email;

public class EmailWelcomeEventProcessor extends WelcomeEventProcessor {
    @Override
    public void on(WelcomeEvent event) {
        new PostOffice().sendWelcome(event.source().who());
    }
}
