package act.fsa.guice.modules;

import act.fsa.guice.GreetingService;
import act.fsa.guice.impl.greeting.GreetingServiceImpl;
import com.google.inject.AbstractModule;

public class GreetingModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GreetingService.class).to(GreetingServiceImpl.class);
    }
}
