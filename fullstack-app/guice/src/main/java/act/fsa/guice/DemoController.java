package act.fsa.guice;

import act.app.AppContext;
import org.osgl.http.H;
import org.osgl.mvc.annotation.Before;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.result.Result;

import javax.inject.Inject;

import static act.controller.Controller.Util.text;

public class DemoController {

    @Before
    public void mockFormat(String fmt, AppContext context) {
        if ("json".equals(fmt)) {
            context.accept(H.Format.json);
        }
        context.session().put("foo", "bar");
    }

    @Inject
    GreetingService greeting;

    @Inject
    HiService hi;

    @Inject
    ByeService bye;

    @Inject
    HomeService home;

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
}
