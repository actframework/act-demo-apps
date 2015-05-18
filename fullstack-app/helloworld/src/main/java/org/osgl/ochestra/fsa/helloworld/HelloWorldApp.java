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

    @Before
    public void mockFormat(String fmt, AppContext context) {
        if ("json".equals(fmt)) {
            context.format(H.Format.json);
        }
    }

    @GetAction("/hello")
    public String sayHello() {
        return "Hello Jband!";
    }

    @GetAction("/bye")
    public void byePlay() {
        text("bye Play!");
    }

    @GetAction("/greeting")
    public Result greeting(String who, int age) {
        return render(who, age);
    }

    public static void main(String[] args) throws Exception {
        RunApp.start(HelloWorldApp.class);
    }
}
