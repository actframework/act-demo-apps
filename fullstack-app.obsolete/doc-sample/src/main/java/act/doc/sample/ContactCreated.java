package act.doc.sample;

import act.event.ActEvent;
import act.event.ActEventListenerBase;
import act.util.Async;

@Async
public class ContactCreated extends ActEventListenerBase<ActEvent<Contact>> {
    @Override
    public void on(ActEvent<Contact> event) throws Exception {
        Contact contact = event.source();
        // send welcome email to contact
    }
}
