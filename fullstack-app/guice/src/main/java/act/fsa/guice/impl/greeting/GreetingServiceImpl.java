package act.fsa.guice.impl.greeting;

import act.fsa.guice.GreetingService;

public class GreetingServiceImpl implements GreetingService {
    @Override
    public String greeting() {
        return "Hello world!";
    }
}
