package act.fsa.email;

import act.boot.app.RunApp;
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
    public Result welcome(String who) {
        logger.info(">> welcome action handler");
        postOffice.sendWelcome(who);
        logger.info("<< welcome action handler");
        return redirect("/");
    }

    @PostAction("/bye")
    public Result bye(String who) {
        logger.info(">> bye action handler");
        postOffice.sendBye(who);
        logger.info("<< bye action handler");
        return redirect("/");
    }


    public static void main(String[] args) throws Exception {
        Thread.sleep(3000);
        RunApp.start(EmailDemoApp.class);
    }

}
