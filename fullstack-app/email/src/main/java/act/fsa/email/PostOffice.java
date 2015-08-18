package act.fsa.email;

import act.conf.AppConfig;
import act.job.Every;
import act.mail.Mailer;
import org.osgl.logging.L;
import org.osgl.logging.Logger;
import org.osgl.util.E;
import org.osgl.util.S;
import sun.awt.AppContext;

import javax.inject.Inject;

import static act.mail.Mailer.Util.*;

@Mailer
public class PostOffice {

    private static Logger logger = L.get(PostOffice.class);

    @Inject
    private AppConfig conf;

    public void sendWelcome(String who) {
        mailer().to(emailOf(who)).subject("Welcome");
        send(who);
    }

    public void sendBye(String who) {
        to(emailOf(who));
        send(who);
    }

    private String emailOf(String who) {
        if (!isValidEmail(who)) {
            logger.warn("Invalid email address: %s", who);
            who = (String)conf.get("app.default.recipient");
            if (!isValidEmail(who)) {
                throw E.invalidConfiguration("configuration of 'app.default.recipient' is not a valid email: %s", who);
            }
        }
        return who;
    }

    private static boolean isValidEmail(String who) {
        if (S.blank(who)) return false;
        return who.toLowerCase().matches("^[_a-z0-9-']+(\\.[_a-z0-9-']+)*(\\+[0-9]+)?@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$");
    }

    @Every("120s")
    @Mailer("notification")
    public void sendNotification() {
        send();
    }

}
