package demo.injection.impl.greeting;

import act.Act;
import act.app.ActionContext;
import act.sys.Env;
import demo.injection.GreetingService;

import javax.inject.Inject;
import javax.inject.Provider;

@Env.Mode(Act.Mode.DEV)
public class MockGreetingServiceImpl implements GreetingService {

    @Inject
    Provider<ActionContext> context;

    @Override
    public String greeting() {
        String who = context.get().paramVal("who");
        if (null == who) {
            who = "world";
        }
        return String.format("Hello %s! in Dev Mode", who);
    }
}
