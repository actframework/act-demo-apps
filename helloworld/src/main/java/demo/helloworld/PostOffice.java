package demo.helloworld;

import act.event.On;
import act.mail.Mailer;
import org.osgl.util.E;
import org.osgl.util.S;

@Mailer
public class PostOffice extends Mailer.Util {

    @On(value = "user-signup", async = true)
    public void sendRegistrationEmail(Person person) {
        HelloWorldApp.LOGGER.info("Sending out registration email to %s", person.getEmail());
        String email = person.getEmail();
        E.illegalArgumentIf(S.blank(email));
        to(email);
        from("dameon@pixolut.com");
        subject("Welcome new user registration");
        send(person);
    }


}
