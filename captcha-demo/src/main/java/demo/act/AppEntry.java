package demo.act;

import act.Act;
import act.app.ActionContext;
import act.controller.Controller;
import act.controller.captcha.RequireCaptcha;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;

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
    public void home() {
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

    public static void main(String[] args) throws Exception {
        Act.start();
    }

}
