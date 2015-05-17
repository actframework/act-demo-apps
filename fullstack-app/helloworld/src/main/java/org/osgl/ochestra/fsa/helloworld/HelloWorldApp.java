package org.osgl.ochestra.fsa.helloworld;

import org.osgl.mvc.annotation.GetAction;
import org.osgl.oms.boot.app.RunApp;

import java.net.URL;

/**
 *
 */
public class HelloWorldApp {
    @GetAction("/hello")
    public String sayHello() {
        return "hello world";
    }

    public static void main(String[] args) throws Exception {
        RunApp.start(HelloWorldApp.class);
    }
}
