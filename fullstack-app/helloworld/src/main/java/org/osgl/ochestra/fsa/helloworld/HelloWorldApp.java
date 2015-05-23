package org.osgl.ochestra.fsa.helloworld;

import org.osgl.http.H;
import org.osgl.mvc.annotation.Before;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.result.Result;
import org.osgl.oms.app.AppContext;
import org.osgl.oms.boot.app.RunApp;

import static org.osgl.oms.controller.Controller.Util.render;
import static org.osgl.oms.controller.Controller.Util.text;

/**
 * The simple hello world app.
 * <p>Run this app, try to update some of the code, then
 * press F5 in the browser to watch the immediate change
 * in the browser!</p>
 */
public class HelloWorldApp {

    AppContext context;

    public HelloWorldApp() {
        System.out.println("The controller initialized");
    }

    @Before
    public void mockFormat(String fmt) {
        if ("json".equals(fmt)) {
            context.format(H.Format.json);
        }
        context.session().put("foo", "bar");
    }

    @GetAction("/hello")
    public String sayHello() {
        return "Hello Ying!";
    }

    @GetAction("/bye")
    public void byePlayAndSpring() {
        text("bye Play and Spring!");
    }

    @GetAction("/greeting")
    public Result greeting(String who, int age) {
        return render(who, age);
    }

    @GetAction("/thank")
    public static String thankYou() {
        return "thank you!";
    }

    public static void main(String[] args) throws Exception {
        RunApp.start(HelloWorldApp.class);
    }

}
