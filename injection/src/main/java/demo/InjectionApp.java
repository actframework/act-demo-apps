package demo;

import act.app.ActionContext;
import act.boot.app.RunApp;
import org.osgl.http.H;
import org.osgl.inject.annotation.Provided;
import org.osgl.mvc.annotation.Before;
import org.osgl.mvc.annotation.GetAction;

import javax.inject.Inject;

import static act.controller.Controller.Util.text;

public class InjectionApp {

    HiService hi;
    ByeService bye;

    @Inject
    public InjectionApp(HiService hi, ByeService bye) {
        this.hi = hi;
        this.bye = bye;
    }

    @Before
    public void mockFormat(ActionContext context, String fmt) {
        if ("json".equals(fmt)) {
            context.accept(H.Format.JSON);
        }
        context.session().put("foo", "bar");
    }


    @GetAction("/greeting")
    public String greeting(@Provided GreetingService greeting) {
        return greeting.greeting();
    }

    @GetAction
    public void home() {
    }

    @GetAction("/hi")
    public String hi(String who) {
        return hi.sayHi(who);
    }

    @GetAction("/bye")
    public void bye(String who) {
        text(bye.bye(who));
    }


    public static void main(String[] args) throws Exception {
        RunApp.start(InjectionApp.class);
    }

}
