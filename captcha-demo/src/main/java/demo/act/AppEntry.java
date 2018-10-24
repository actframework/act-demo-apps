package demo.act;

import act.Act;
import act.app.ActionContext;
import act.app.App;
import act.controller.Controller;
import act.controller.captcha.RequireCaptcha;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.util.S;

/**
 * A simple hello world app entry
 *
 * Run this app, try to update some of the code, then
 * press F5 in the browser to watch the immediate change
 * in the browser!
 */
@SuppressWarnings("unused")
public class AppEntry extends Controller.Util {

    /**
     * Load the form
     */
    @GetAction
    public void home(App app) {
    }

    /**
     * Process POST request.
     * @param name the name data
     * @param ctx the context injected
     */
    @PostAction
    @RequireCaptcha
    public void post(String name, ActionContext ctx) {
        if (ctx.hasViolation()) {
            ctx.flash().error(ctx.violationMessage());
        } else {
            ctx.flash("result", name);
        }
        redirect("/");
    }

    @GetAction("cache_test")
    public void cacheTest(App app) {
        app.cache().put("foo", S.random());
        app.cache("5").put("foo", "five");
        app.cache("50").put("foo", "fifty");
    }

    public static void main(String[] args) throws Exception {
        Act.start();
    }

}
