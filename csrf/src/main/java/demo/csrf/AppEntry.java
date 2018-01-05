package demo.csrf;

import static act.controller.Controller.Util.redirect;

import act.Act;
import act.app.ActionContext;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;

@SuppressWarnings("unused")
public class AppEntry {

    @GetAction
    public void home() {
    }

    @PostAction
    public void onSubmit(String message, ActionContext context) {
        context.flash("message", message);
        redirect("/");
    }

    public static void main(String[] args) throws Exception {
        Act.start();
    }

}
