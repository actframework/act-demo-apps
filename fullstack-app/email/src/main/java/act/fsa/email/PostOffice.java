package act.fsa.email;

import act.job.Every;
import act.mail.Mailer;

import static act.mail.Mailer.Util.*;

@Mailer
public class PostOffice {

    public void sendWelcome(String who) {
        mailer().to(emailOf(who)).subject("Welcome");
        send(who);
    }

    public void sendBye(String who) {
        to(emailOf(who));
        send(who);
    }

    private static String emailOf(String who) {
        return "greenlaw110+" + who + "@gmail.com";
    }

    @Every("120s")
    @Mailer("notification")
    public void sendNotification() {
        send();
    }

}
