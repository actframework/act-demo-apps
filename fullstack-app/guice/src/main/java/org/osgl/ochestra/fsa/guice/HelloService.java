package org.osgl.ochestra.fsa.guice;

public class HelloService implements DemoService {
    @Override
    public String greeting() {
        return "Hello world";
    }
}
