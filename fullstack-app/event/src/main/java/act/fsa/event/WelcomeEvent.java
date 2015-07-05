package act.fsa.event;

import act.event.ActEvent;

public class WelcomeEvent extends ActEvent<EventDemoApp> {
    public WelcomeEvent(EventDemoApp source) {
        super(source);
    }
}
