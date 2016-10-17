package demo.injection.modules;

import act.inject.ModuleTag;
import demo.injection.GreetingService;
import demo.injection.impl.greeting.GreetingServiceImpl;
import org.osgl.inject.annotation.Provides;

@ModuleTag
public class GreetingModule  {

    @Provides
    public GreetingService greetingService(GreetingServiceImpl greetingService) {
        return greetingService;
    }

}
