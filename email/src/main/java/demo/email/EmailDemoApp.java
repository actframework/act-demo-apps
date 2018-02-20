package demo.email;

/*-
 * #%L
 * actframework app demo - email
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

import static act.controller.Controller.Util.redirect;

import act.Act;
import act.app.ActionContext;
import act.util.LogSupport;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;

import javax.inject.Inject;

public class EmailDemoApp extends LogSupport {

    @Inject
    private PostOffice postOffice;

    @GetAction("/")
    public void home() {
    }

    @PostAction("/welcome")
    public void welcome(String who, ActionContext context) {
        info(">> welcome action handler");
        if (!PostOffice.isValidEmail(who)) {
            context.flash().error("Please type in valid email");
        } else {
            postOffice.sendWelcome(who);
            context.flash().success("welcome email sent");
        }
        info("<< welcome action handler");
        redirect("/");
    }

    @PostAction("/bye")
    public void bye(String who, ActionContext context) {
        info(">> bye action handler");
        if (!PostOffice.isValidEmail(who)) {
            context.flash().error("Please type in valid email");
        } else {
            postOffice.sendBye(who);
            context.flash().success("farewell email sent");
        }
        info("<< bye action handler");
        redirect("/");
    }


    public static void main(String[] args) throws Exception {
        Act.start();
    }

}
