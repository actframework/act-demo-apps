package demo.event;

import act.event.ActEventListenerBase;
import org.osgl.logging.L;
import org.osgl.logging.Logger;

public abstract class WelcomeEventProcessor extends ActEventListenerBase<WelcomeEvent> {
    protected static Logger logger = L.get(WelcomeEventProcessor.class);
}
