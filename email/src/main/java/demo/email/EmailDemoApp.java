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
    public Result home() {
        return render();
    }

    @PostAction("/welcome")
    public Result welcome(String who, ActionContext context) {
        logger.info(">> welcome action handler");
        if (!PostOffice.isValidEmail(who)) {
            context.flash().error("Please type in valid email");
        } else {
            postOffice.sendWelcome(who);
            context.flash().success("welcome email sent");
        }
        logger.info("<< welcome action handler");
        return redirect("/");
    }

    @PostAction("/bye")
    public Result bye(String who, ActionContext context) {
        logger.info(">> bye action handler");
        if (!PostOffice.isValidEmail(who)) {
            context.flash().error("Please type in valid email");
        } else {
            postOffice.sendBye(who);
            context.flash().success("farewell email sent");
        }
        logger.info("<< bye action handler");
        return redirect("/");
    }


    public static void main(String[] args) throws Exception {
        Act.start("Email Demo");
    }

}
