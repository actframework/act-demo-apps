package org.osgl.ochestra.fsa.helloworld;

import org.osgl.mvc.annotation.GetAction;
import org.osgl.oms.boot.app.RunApp;

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
        return "Hello Ochestra!";
    }

    @GetAction("/bye")
    public void byePlay() {
        text("bye Play!");
    }

    public static void main(String[] args) throws Exception {
        RunApp.start(HelloWorldApp.class);
    }
}
