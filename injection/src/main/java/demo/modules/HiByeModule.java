package demo.modules;

import act.sys.Env;
import demo.ByeService;
import demo.HiService;
import demo.impl.bye.ByeServiceImpl;
import demo.impl.hi.HiServiceImpl;
import org.osgl.inject.Module;

import static act.Act.Mode.PROD;

@Env.Mode(PROD)
public class HiByeModule extends Module {
    @Override
    protected void configure() {
        bind(HiService.class).to(HiServiceImpl.class);
        bind(ByeService.class).to(ByeServiceImpl.class);
    }
}
