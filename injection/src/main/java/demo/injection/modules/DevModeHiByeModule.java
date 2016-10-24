package demo.injection.modules;

import act.Act;
import act.inject.ModuleTag;
import act.sys.Env;
import demo.injection.ByeService;
import demo.injection.HiService;
import demo.injection.impl.bye.MockByeServiceImpl;
import demo.injection.impl.hi.MockHiServiceImpl;
import org.osgl.inject.Module;
import org.osgl.inject.annotation.Provides;
import org.osgl.inject.provider.LazyProvider;


import javax.inject.Provider;

import static act.Act.Mode.DEV;

/**
 * This module demonstrate how to create module by providing
 * factory methods
 *
 * @see HiByeModule
 */
@ModuleTag
@Env.Mode(DEV)
public class DevModeHiByeModule {

    @Provides
    public static HiService foo(MockHiServiceImpl hiService) {
        return hiService;
    }

    @Provides
    public ByeService bar() {
        return Act.newInstance(MockByeServiceImpl.class);
    }
}
