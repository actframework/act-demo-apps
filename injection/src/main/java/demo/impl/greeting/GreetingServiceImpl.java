package demo.impl.greeting;

import act.app.ActionContext;
import demo.GreetingService;

import javax.inject.Inject;
import javax.inject.Provider;

public class GreetingServiceImpl implements GreetingService {

    @Inject
    Provider<ActionContext> context;

    @Override
    public String greeting() {
        String who = context.get().paramVal("who");
        if (null == who) {
            who = "world";
        }
        return String.format("Hello %s!", who);
    }
}
