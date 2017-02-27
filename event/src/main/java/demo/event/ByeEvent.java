package demo.event;

import act.event.ActEvent;

public class ByeEvent extends ActEvent<EventDemoApp> {
    public ByeEvent(EventDemoApp source) {
        super(source);
    }
}
