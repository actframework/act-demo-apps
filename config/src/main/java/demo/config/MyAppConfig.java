package demo.config;

/*-
 * #%L
 * actframework app demo - configuration
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

import act.app.conf.AppConfigurator;

/**
 * Application could be configured using code if configuration file
 * is not your favorite. Just create a public class that extends
 * {@link act.app.conf.AppConfigurator} and override the {@link act.app.conf.AppConfigurator#configure()}
 * method. ACT framework will sense your code base configuration and load it
 * during bootstrap process
 */
public class MyAppConfig extends AppConfigurator<MyAppConfig> {

    // So override the configure() method
    // to do our code based configurations
    @Override
    public void configure() {
        configureTemplateHome();
        configureAppProps();
    }

    private void configureTemplateHome() {
        // this set the template home path to "views"
        // by default it is "default", which in the end
        // mapped to "rythm" because default view engine is rythm
        templateHome("views");
    }

    private void configureAppProps() {
        prop("x.to", "X-man");
    }

}
