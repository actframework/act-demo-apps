package demo.injection.modules;

import act.sys.Env;
import demo.injection.ByeService;
import demo.injection.HiService;
import demo.injection.impl.bye.MockByeServiceImpl;
import demo.injection.impl.hi.MockHiServiceImpl;
import org.osgl.inject.Module;

import static act.Act.Mode.DEV;

@Env.Mode(DEV)
public class DevModeHiByeModule extends Module {
    @Override
    protected void configure() {
        bind(HiService.class).to(MockHiServiceImpl.class);
        bind(ByeService.class).to(MockByeServiceImpl.class);
    }
}
