package demo.helloworld.gh203;

import act.controller.annotation.TemplateContext;
import act.mail.Mailer;

@Mailer
@TemplateContext("/mail")
public class PostOffice extends Mailer.Util {

    public void sendEmail() {
        to("someone@some.where");
        subject("Email subject");
        send();
    }

}
