package demo.injection.modules;

import act.sys.Env;
import demo.injection.ByeService;
import demo.injection.HiService;
import demo.injection.impl.bye.ByeServiceImpl;
import demo.injection.impl.hi.HiServiceImpl;
import org.osgl.inject.Module;

import static act.Act.Mode.PROD;

/**
 * This module demonstrate how to do binding by
 * extending {@link Module} and calling bind API
 * in the {@link Module#configure()} method
 *
 * @see DevModeHiByeModule
 */
@Env.Mode(PROD)
public class HiByeModule extends Module {
    @Override
    protected void configure() {
        bind(HiService.class).to(HiServiceImpl.class);
        bind(ByeService.class).to(ByeServiceImpl.class);
    }
}
