package demo.helloworld;

import act.Version;
import act.aaa.LoginUser;
import act.app.ActionContext;
import act.boot.app.RunApp;
import act.job.Every;
import org.osgl.aaa.NoAuthentication;
import org.osgl.http.H;
import org.osgl.inject.annotation.Provided;
import org.osgl.logging.LogManager;
import org.osgl.logging.Logger;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.Param;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.annotation.SessionFree;
import org.osgl.mvc.result.Result;
import org.osgl.util.C;
import org.osgl.util.S;

import javax.inject.Inject;
import javax.inject.Singleton;

import static act.controller.Controller.Util.*;

/**
 * The simple hello world app.
 * <p>Run this app, try to update some of the code, then
 * press F5 in the browser to watch the immediate change
 * in the browser!</p>
 */
@SuppressWarnings("unused")
@Singleton
public class HelloWorldApp {

    public static final Logger LOGGER = LogManager.get("demo.helloworld");

    @GetAction
    public void home(@LoginUser Person me) {
        renderTemplate(me);
    }

    @GetAction("/login")
    @NoAuthentication
    public void loginForm() {
    }

    @PostAction("/login")
    @NoAuthentication
    public void login(String username, String password, @Provided Person.Dao dao, ActionContext context) {
        Person person = dao.authenticate(username, password);
        if (null == person) {
            context.flash().error("Unknown username password");
            redirect("/login");
        }
        context.session().put("username", person.getEmail());
        redirect("/");
    }

    @Every("3s")
    public static void bar() {
        System.out.println("bar....");
    }

    @Every("6s")
    public static void foo() {
        System.out.println("FOO....");
    }

    public static void main(String[] args) throws Exception {
        RunApp.start("HelloWorld", Version.appVersion(), HelloWorldApp.class);
    }

}
