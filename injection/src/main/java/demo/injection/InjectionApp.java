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

import act.Act;
import org.osgl.mvc.annotation.Action;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.With;
import org.osgl.mvc.result.Result;

import javax.inject.Inject;

import static act.controller.Controller.Util.render;
import static act.controller.Controller.Util.text;

@SuppressWarnings("unused")
@With(SomeOtherClass.class)
public class InjectionApp {

    @Inject
    private HiService hi;

    @Inject
    private ByeService bye;

    @GetAction
    public void home() {
    }

    @GetAction("/greeting")
    public String greeting(GreetingService greeting) {
        return greeting.greeting();
    }

    @Action("/hi")
    public void hi(String who) {
        String message = hi.sayHi(who);
        render(message);
    }

    @Action("/bye")
    public void bye(String who) {
        text(bye.bye(who));
    }

    public static void main(String[] args) throws Exception {
        Act.start();
    }

}
