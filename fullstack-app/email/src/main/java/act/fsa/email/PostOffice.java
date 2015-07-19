package act.fsa.email;

import act.job.Every;
import act.mail.Mailer;

import static act.mail.Mailer.Util.*;

@Mailer("default")
public class PostOffice {

    public void sendWelcome(String who) {
        to(emailOf(who));
        send(who);
    }

    public void sendBye(String who) {
        to(emailOf(who));
        send(who);
    }

    private static String emailOf(String who) {
        return who + "@xxx.com";
    }

    @Every("10s")
    @Mailer("notification")
    public void sendNotification() {
        send();
    }

}
