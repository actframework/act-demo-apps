package demo.modules;

import act.inject.ModuleTag;
import demo.GreetingService;
import demo.impl.greeting.GreetingServiceImpl;
import org.osgl.inject.Module;
import org.osgl.inject.annotation.Provides;

@ModuleTag
public class GreetingModule  {

    @Provides
    public GreetingService greetingService(GreetingServiceImpl greetingService) {
        return greetingService;
    }

}
