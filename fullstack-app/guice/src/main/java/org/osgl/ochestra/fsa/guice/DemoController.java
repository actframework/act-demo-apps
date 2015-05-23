package org.osgl.ochestra.fsa.guice;

import org.osgl.mvc.annotation.GetAction;

import javax.inject.Inject;

public class DemoController {

    public DemoController() {
        System.out.println("Controller initialized");
    }

    @Inject
    DemoService service;

    @GetAction("/greeting")
    public String greeting() {
        return service.greeting();
    }
}
