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

import act.Act;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.result.Result;

import static act.controller.Controller.Util.render;
import static act.controller.Controller.Util.text;

/**
 * App entry for Config Demo project
 */
public class ConfigDemo {

    // This is the root URL handler. It will load the template
    // stored in resources/views/demo/config/ConfigDemo.home.html
    // Note the template files are not sit under `rythm` because
    // in the MyAppConfig class we configured the `templateHome`
    /// to be "views"
    @GetAction
    public void home() {
    }

    // This is going to be overwritten by routes.conf
    @GetAction("/bye")
    public void byeSpring() {
        text("bye Spring!!");
    }

    // This will display all application setttings
    @GetAction("/setting")
    public static void showAppSettings() {
    }

    public static void main(String[] args) throws Exception {
        Act.start();
    }

}
