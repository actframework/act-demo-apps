package act.fsa.event;

import act.boot.app.RunApp;
import act.event.EventBus;
import org.osgl.logging.L;
import org.osgl.logging.Logger;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.result.Result;

import javax.inject.Inject;

import static act.controller.Controller.Util.redirect;
import static act.controller.Controller.Util.render;

public class EventDemoApp {

    private static Logger logger = L.get(EventDemoApp.class);

    private String who;

    @Inject
    private EventBus eventBus;

    public String who() {
        return who;
    }

    @GetAction("/")
    public Result home() {
        return render();
    }

    @PostAction("/welcome")
    public Result welcome(String who) {
        logger.info(">> welcome action handler");
        this.who = who;
        eventBus.emit(new WelcomeEvent(this));
        logger.info("<< welcome action handler");
        return redirect("/");
    }

    @PostAction("/bye")
    public Result bye(String who) {
        logger.info(">> bye action handler");
        this.who = who;
        eventBus.emitSync(new ByeEvent(this));
        logger.info("<< bye action handler");
        return redirect("/");
    }


    public static void main(String[] args) throws Exception {
        RunApp.start(EventDemoApp.class);
    }

}
