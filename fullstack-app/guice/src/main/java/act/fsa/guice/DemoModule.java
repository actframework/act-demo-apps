package act.fsa.guice;

import com.google.inject.AbstractModule;

/**
 * Created by luog on 23/05/15.
 */
public class DemoModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DemoService.class).to(HelloService.class);
    }
}
