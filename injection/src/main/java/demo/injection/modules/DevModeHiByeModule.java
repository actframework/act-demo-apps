package demo.injection.modules;

/*-
 * #%L
 * actframework app demo - dependency injection
 * %%
 * Copyright (C) 2018 ActFramework
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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
