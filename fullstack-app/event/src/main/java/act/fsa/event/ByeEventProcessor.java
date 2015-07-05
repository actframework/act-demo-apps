package act.fsa.event;

import act.event.ActEventListenerBase;
import org.osgl.logging.L;
import org.osgl.logging.Logger;

public abstract class ByeEventProcessor extends ActEventListenerBase<ByeEvent> {
    protected static Logger logger = L.get(ByeEventProcessor.class);
}
