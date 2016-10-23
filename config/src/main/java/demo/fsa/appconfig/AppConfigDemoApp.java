package act.fsa.appconfig;

import act.app.ActionContext;
import act.boot.app.RunApp;
import org.osgl.http.H;
import org.osgl.mvc.annotation.Before;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.result.Result;

import static act.controller.Controller.Util.*;

/**
 * The simple hello world app.
 * <p>Run this app, try to update some of the code, then
 * press F5 in the browser to watch the immediate change
 * in the browser!</p>
 */
public class AppConfigDemoApp {

    @Before
    public void mockFormat(String fmt, ActionContext context) {
        if ("json".equals(fmt)) {
            context.accept(H.Format.json);
        }
        context.session().put("foo", "bar");
    }

    @GetAction("/hello")
    public String sayHello() {
        return "Hello Act!";
    }

    @GetAction("/bye")
    public void byePlayAndSpring() {
        text("bye Play and Spring!!");
    }

    @GetAction("/greeting")
    public void greeting(String who, int age) {
        render(who, age);
    }

    @GetAction("/thank")
    public static String thankYou() {
        return "thank you!";
    }

    @GetAction("/setting")
    public static Result showAppSettings() {
        return render();
    }

    public static void main(String[] args) throws Exception {
        RunApp.start(AppConfigDemoApp.class);
    }

}
