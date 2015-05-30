package act.fsa.guice.modules;

import act.fsa.guice.ByeService;
import act.fsa.guice.HiService;
import act.fsa.guice.impl.bye.ByeServiceImpl;
import act.fsa.guice.impl.hi.HiServiceImpl;
import com.google.inject.AbstractModule;

public class HiByeModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(HiService.class).to(HiServiceImpl.class);
        bind(ByeService.class).to(ByeServiceImpl.class);
    }
}
