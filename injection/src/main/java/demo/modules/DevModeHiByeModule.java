package demo.modules;

import act.sys.Env;
import demo.ByeService;
import demo.HiService;
import demo.impl.bye.ByeServiceImpl;
import demo.impl.bye.MockByeServiceImpl;
import demo.impl.hi.HiServiceImpl;
import demo.impl.hi.MockHiServiceImpl;
import org.osgl.inject.Module;

import static act.Act.Mode.DEV;
import static act.Act.Mode.PROD;

@Env.Mode(DEV)
public class DevModeHiByeModule extends Module {
    @Override
    protected void configure() {
        bind(HiService.class).to(MockHiServiceImpl.class);
        bind(ByeService.class).to(MockByeServiceImpl.class);
    }
}
