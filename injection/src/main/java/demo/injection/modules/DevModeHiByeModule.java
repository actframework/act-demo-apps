package demo.injection.modules;

import static act.Act.Mode.DEV;

import act.Act;
import act.sys.Env;
import demo.injection.ByeService;
import demo.injection.HiService;
import demo.injection.impl.bye.MockByeServiceImpl;
import demo.injection.impl.hi.MockHiServiceImpl;
import org.osgl.inject.annotation.Provides;

/**
 * This module demonstrate how to create module by providing
 * factory methods
 *
 * @see HiByeModule
 */
@Env.Mode(DEV)
public class DevModeHiByeModule {

    @Provides
    public static HiService foo(MockHiServiceImpl hiService) {
        return hiService;
    }

    @Provides
    public ByeService bar() {
        return Act.getInstance(MockByeServiceImpl.class);
    }

}
