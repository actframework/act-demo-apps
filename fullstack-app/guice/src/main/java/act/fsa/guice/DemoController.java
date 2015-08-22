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

    App app;
    GreetingService greeting;
    HiService hi;
    ByeService bye;
    HomeService home;
    ByeServiceImpl2 bye2;

    @Inject
    public DemoController(App app, GreetingService greeting, HiService hi, ByeService bye, HomeService home, ByeServiceImpl2 bye2) {
        this.app = app;
        this.greeting = greeting;
        this.hi = hi;
        this.bye = bye;
        this.home = home;
        this.bye2 = bye2;
    }

    @Before
    public void mockFormat(String fmt, ActionContext context) {
        if ("json".equals(fmt)) {
            context.accept(H.Format.json);
        }
        context.session().put("foo", "bar");
    }


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
        text(bye2.zaijian());
    }
}
