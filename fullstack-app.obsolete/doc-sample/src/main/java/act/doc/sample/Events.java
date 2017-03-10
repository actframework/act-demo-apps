package act.doc.sample;

import act.db.ebean.EbeanDao;
import act.db.ebean.EbeanService;
import act.event.On;
import act.mail.Mailer;
import act.util.Async;
import org.osgl.util.S;

import javax.inject.Inject;

@Mailer
public class Events extends Mailer.Util {

    @On("new-contact")
    @Async
    public void sendWelcomeMail(Contact contact) {
        to(contact.getEmail());
        send(contact);
    }

    @On("new-contact")
    public void printLog(Contact contact) {
        System.out.println(S.fmt("%s created", contact.getFirstName()));
    }

}
