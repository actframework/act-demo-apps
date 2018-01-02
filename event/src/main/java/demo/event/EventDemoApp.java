package demo.event;

/*-
 * #%L
 * actframework app demo - event
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
import act.event.EventBus;
import act.event.On;
import act.util.Async;
import org.joda.time.DateTime;
import org.osgl.logging.L;
import org.osgl.logging.Logger;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.result.Result;

import javax.inject.Inject;

import static act.controller.Controller.Util.redirect;
import static act.controller.Controller.Util.render;

public class EventDemoApp {

    private static Logger logger = L.get(EventDemoApp.class);

    private String who;

    @Inject
    private EventBus eventBus;

    public String who() {
        return who;
    }

    @GetAction("/")
    public Result home() {
        return render();
    }

    @PostAction("/welcome")
    public Result welcome(String who) {
        logger.info(">> welcome action handler");
        this.who = who;
        eventBus.emit(new WelcomeEvent(this));
        logger.info("<< welcome action handler");
        return redirect("/");
    }

    @PostAction("/bye")
    public Result bye(String who) {
        logger.info(">> bye action handler");
        this.who = who;
        eventBus.emitSync(new ByeEvent(this));
        logger.info("<< bye action handler");
        return redirect("/");
    }

    @PostAction("/async")
    public Result async(String who) {
        logger.info(">> async action handler");
        eventBus.emit(new AsyncEvent(who));
        logger.info("<< async action handler");
        return redirect("/");
    }

    public static class User {
        public static final String SIGN_UP = "user-sign-up";
        public String name;
        public User(String name) {
            this.name = name;
        }
    }

    @PostAction("/signUp")
    public void signUp(String username) {
        logger.info(">> user sign up");
        eventBus.trigger(User.SIGN_UP, new User(username), DateTime.now());
        logger.info("<< user sign up");
        redirect("/");
    }

    @On(User.SIGN_UP)
    @Async
    public static void handleUserSignUpEmail(User user, DateTime when) {
        logger.info("[Email]User[%s] logged in at %s", user.name, when);
    }

    @On(User.SIGN_UP)
    public static void handleUserSignUpTextMsg(User user, DateTime when) {
        logger.info("[TextMsg]User[%s] logged in at %s", user.name, when);
    }

    public static void main(String[] args) throws Exception {
        Act.start();
    }

}
