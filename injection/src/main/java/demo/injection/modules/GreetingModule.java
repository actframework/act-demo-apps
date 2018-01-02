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

import demo.injection.GreetingService;
import demo.injection.impl.greeting.GreetingServiceImpl;
import org.osgl.inject.Module;

/**
 * Overwrite the auto binding on {@link GreetingService} so we always
 * use {@link GreetingServiceImpl} in regarding to the current app
 * run environment mode.
 */
public class GreetingModule extends Module {
    @Override
    protected void configure() {
        // Comment out the following line and refresh your browser page
        // at http://localhost:5460/greeting to observe the changes
        // bind(GreetingService.class).to(GreetingServiceImpl.class);
    }
}
