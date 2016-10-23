package demo.injection;

import act.Version;
import act.app.ActionContext;
import act.boot.app.RunApp;
import act.security.CSRF;
import org.osgl.http.H;
import org.osgl.inject.annotation.Provided;
import org.osgl.mvc.annotation.Before;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.annotation.PutAction;
import org.osgl.mvc.result.Result;
import org.osgl.util.C;

import javax.inject.Inject;

import java.util.Map;

import static act.controller.Controller.Util.jsonMap;
import static act.controller.Controller.Util.render;
import static act.controller.Controller.Util.text;

public class InjectionApp {

    private HiService hi;
    private ByeService bye;

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
    public String greeting(GreetingService greeting) {
        return greeting.greeting();
    }

    @GetAction
    public void home() {
    }

    @PostAction("/hi")
    public Result hi(String who) {
        String message = hi.sayHi(who);
        return render(message);
    }

    @PostAction("/bye")
    public void bye(String who) {
        text(bye.bye(who));
    }

    @PutAction("/customer/{id}")
    @CSRF.Disable
    public Result updateCustomer(int id, String name, String age) {
        return jsonMap(id, name, age);
    }


    public static void main(String[] args) throws Exception {
        RunApp.start("Inject Demo", Version.appVersion(), InjectionApp.class);
    }

}
