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
