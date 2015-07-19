package act.fsa.email;

import act.event.ActEvent;

public class ByeEvent extends ActEvent<EmailDemoApp> {
    public ByeEvent(EmailDemoApp source) {
        super(source);
    }
}
