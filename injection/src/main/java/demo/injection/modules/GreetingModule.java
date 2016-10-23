package demo.injection.modules;

import demo.injection.GreetingService;
import demo.injection.impl.greeting.GreetingServiceImpl;
import org.osgl.inject.annotation.Provides;

public class GreetingModule  {
    @Provides
    public GreetingService greetingService(GreetingServiceImpl greetingService) {
        return greetingService;
    }
}
