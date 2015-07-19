package act.fsa.guice;

import act.app.App;
import act.app.ActionContext;
import act.fsa.guice.impl.bye.ByeServiceImpl2;
import org.osgl.http.H;
import org.osgl.mvc.annotation.Before;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.result.Result;

import javax.inject.Inject;

import static act.controller.Controller.Util.text;

public class DemoController {

    @Before
    public void mockFormat(String fmt, ActionContext context) {
        if ("json".equals(fmt)) {
            context.accept(H.Format.json);
        }
        context.session().put("foo", "bar");
    }

    @Inject
    GreetingService greeting;

    @Inject
    App app;

    @Inject
    HiService hi;

    @Inject
    ByeService bye;

    @Inject
    HomeService home;

    @Inject
    ByeServiceImpl2 bye2;

    @GetAction("/")
    public Result welcome() {
        return home.welcome();
    }

    @GetAction("/greeting")
    public String greeting() {
        return greeting.greeting();
    }

    @PostAction("/hi")
    public void hi(String who) {
        text(hi.sayHi(who));
    }

    @PostAction("/bye")
    public void bye(String who) {
        text(bye.bye(who));
    }

    @GetAction("/zaijian")
    public void zaijian() {
        ByeServiceImpl2 b2 = app.newInstance(ByeServiceImpl2.class);
        text(b2.zaijian());
    }
}
