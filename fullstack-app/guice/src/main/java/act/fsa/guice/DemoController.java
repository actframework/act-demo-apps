package act.fsa.guice;

import act.app.AppContext;
import act.controller.Controller;
import org.osgl.http.H;
import org.osgl.mvc.annotation.Before;
import org.osgl.mvc.annotation.GetAction;

import javax.inject.Inject;

import static act.controller.Controller.Util.text;

public class DemoController {

    @Before
    public void mockFormat(String fmt, AppContext context) {
        if ("json".equals(fmt)) {
            context.format(H.Format.json);
        }
        context.session().put("foo", "bar");
    }

    @Inject
    GreetingService greeting;

    @Inject
    HiService hi;

    @Inject
    ByeService bye;

    @GetAction("/greeting")
    public String greeting() {
        return greeting.greeting();
    }

    @GetAction("/hi")
    public void hi(String who) {
        text(hi.sayHi(who));
    }

    @GetAction("/bye")
    public void bye(String who) {
        text(bye.bye(who));
    }
}
