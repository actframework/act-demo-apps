package demo.featuretest;

/*-
 * #%L
 * actframework Feature Test App
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

import static act.controller.annotation.Throttled.ExpireScale.ENABLED;

import act.controller.annotation.Throttled;
import org.osgl.mvc.annotation.GetAction;

public class GH435 {

    @GetAction("gh/435")
    @Throttled(1) // maximum 1 requests per second from the same ip
    public String test() {
        return "GH435 - throttle control";
    }

    @GetAction("gh/435/2")
    @Throttled(value = 1, expireScale = ENABLED)
    public String test2() {
        return "GH435 - throttle control with expire scale";
    }

}
