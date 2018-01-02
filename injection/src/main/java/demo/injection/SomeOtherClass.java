package demo.injection;

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

import act.app.ActionContext;
import org.osgl.http.H;
import org.osgl.mvc.annotation.Before;

public class SomeOtherClass {

    @Before(only = "greeting")
    public void mockFormat(String fmt, ActionContext context) {
        if ("json".equals(fmt)) {
            context.accept(H.Format.JSON);
        }
    }

}
