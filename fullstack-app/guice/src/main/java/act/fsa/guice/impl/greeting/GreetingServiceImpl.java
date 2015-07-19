package act.fsa.guice.impl.greeting;

import act.app.ActionContext;
import act.fsa.guice.GreetingService;

import javax.inject.Inject;

public class GreetingServiceImpl implements GreetingService {

    @Inject
    ActionContext context;

    @Override
    public String greeting() {
        String who = context.paramVal("who");
        if (null == who) {
            who = "world";
        }
        return String.format("Hello %s!", who);
    }
}
