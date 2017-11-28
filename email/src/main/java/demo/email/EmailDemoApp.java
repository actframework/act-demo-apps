package demo.email;

import act.Act;
import act.app.ActionContext;
import org.osgl.logging.L;
import org.osgl.logging.Logger;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.result.Result;

import javax.inject.Inject;

import static act.controller.Controller.Util.redirect;
import static act.controller.Controller.Util.render;

public class EmailDemoApp {

    private static Logger logger = L.get(EmailDemoApp.class);

    @Inject
    private PostOffice postOffice;

    @GetAction("/")
    public void home() {
    }

    @PostAction("/welcome")
    public void welcome(String who, ActionContext context) {
        logger.info(">> welcome action handler");
        if (!PostOffice.isValidEmail(who)) {
            context.flash().error("Please type in valid email");
        } else {
            postOffice.sendWelcome(who);
            context.flash().success("welcome email sent");
        }
        logger.info("<< welcome action handler");
        redirect("/");
    }

    @PostAction("/bye")
    public void bye(String who, ActionContext context) {
        logger.info(">> bye action handler");
        if (!PostOffice.isValidEmail(who)) {
            context.flash().error("Please type in valid email");
        } else {
            postOffice.sendBye(who);
            context.flash().success("farewell email sent");
        }
        logger.info("<< bye action handler");
        redirect("/");
    }


    public static void main(String[] args) throws Exception {
        Act.start();
    }

}
