package org.osgl.ochestra.fsa.helloworld;

import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.result.Result;
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
    @GetAction("/hello")
    public String sayHello() {
        return "Hello Jband!";
    }

    @GetAction("/bye")
    public void byePlay() {
        text("bye Play!");
    }

    @GetAction("/greeting")
    public Result greeting(String who) {
        return render(who);
    }

    public static void main(String[] args) throws Exception {
        RunApp.start(HelloWorldApp.class);
    }
}
