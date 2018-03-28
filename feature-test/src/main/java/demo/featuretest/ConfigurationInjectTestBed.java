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

import act.handler.UnknownHttpMethodProcessor;
import org.osgl.inject.annotation.Configuration;
import org.osgl.mvc.annotation.GetAction;

public class ConfigurationInjectTestBed {

    @Configuration("handler.unknown_http_method.impl")
    UnknownHttpMethodProcessor p1;

    @Configuration("act.handler.unknown_http_method.impl")
    UnknownHttpMethodProcessor p2;

    @Configuration("handler.unknown_http_method")
    UnknownHttpMethodProcessor p3;

    @GetAction("/inject/conf")
    public String check() {
        if (null == p1) {
            return "p1 should not be null";
        }
        if (p1 != p2 || p2 != p3) {
            return "p1 p2 and p3 shall be the same instance";
        }
        return "success";
    }
}
