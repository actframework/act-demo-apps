package act.fsa.email;

import act.event.ActEvent;

public class WelcomeEvent extends ActEvent<EmailDemoApp> {
    public WelcomeEvent(EmailDemoApp source) {
        super(source);
    }
}
