package demo.injection.modules;

import demo.injection.GreetingService;
import demo.injection.impl.greeting.GreetingServiceImpl;
import org.osgl.inject.Module;

/**
 * Overwrite the auto binding on {@link GreetingService} so we always
 * use {@link GreetingServiceImpl} in regarding to the current app
 * run environment mode.
 */
public class GreetingModule extends Module {
    @Override
    protected void configure() {
        // Comment out the following line and refresh your browser page
        // at http://localhost:5460/greeting to observe the changes
        bind(GreetingService.class).to(GreetingServiceImpl.class);
    }
}
