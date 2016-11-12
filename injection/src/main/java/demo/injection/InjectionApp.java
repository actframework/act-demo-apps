package demo.injection;

import act.Version;
import act.app.ActionContext;
import act.boot.app.RunApp;
import act.security.CSRF;
import org.osgl.http.H;
import org.osgl.inject.annotation.Provided;
import org.osgl.mvc.annotation.*;
import org.osgl.mvc.result.Result;

import javax.inject.Inject;
import javax.inject.Named;

import java.util.List;
import java.util.Set;

import static act.controller.Controller.Util.*;

@SuppressWarnings("unused")
@With(SomeOtherClass.class)
public class InjectionApp {

    private HiService hi;
    private ByeService bye;

    @Before
    public void mockFormat(String fmt, ActionContext context) {
        if ("json".equals(fmt)) {
            context.accept(H.Format.JSON);
        }
    }

    @Inject
    public InjectionApp(HiService hi, ByeService bye) {
        this.hi = hi;
        this.bye = bye;
    }

    @GetAction
    public void home() {
    }

    @GetAction("/greeting")
    public String greeting(GreetingService greeting) {
        return greeting.greeting();
    }

    @Action("/hi")
    public Result hi(String who) {
        String message = hi.sayHi(who);
        return render(message);
    }

    @PostAction("/bye")
    public void bye(String who) {
        text(bye.bye(who));
    }

    public static void main(String[] args) throws Exception {
        RunApp.start("Inject Demo", Version.appVersion(), InjectionApp.class);
    }

}
