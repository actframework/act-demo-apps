package act.fsa.validation;

import act.app.ActionContext;
import act.boot.app.RunApp;
import org.osgl._;
import org.osgl.http.H;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.result.Result;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static act.controller.Controller.Util.render;
import static act.controller.Controller.Util.text;

/**
 * The simple hello world app.
 * <p>Run this app, try to update some of the code, then
 * press F5 in the browser to watch the immediate change
 * in the browser!</p>
 */
public class ValidationDemoApp {

    ActionContext context;

    @GetAction("/")
    public Result home() {
        return render();
    }

    @GetAction("/notNull")
    public Result notNull(@NotNull H.Format fmt) {
        if (context.hasViolation()) {
            return text("Error(s): \n%s", context.violationMessage());
        }
        return text("not null success with %s", fmt);
    }

    @GetAction("/size")
    public void size(@Size(max = 5) String text, @Size(min = 2) List<H.Format> list) {
        if (context.hasViolation()) {
            text("Error(s): \n%s", context.violationMessage());
        }
        text("size success with %s and %s", text, _.toString2(list));
    }

    @GetAction("/digits")
    public void digits(@Digits(integer = 4, fraction = 2) String str, @Digits(integer = 2, fraction = 0) Integer num) {
        if (context.hasViolation()) {
            text("Error(s): \n%s", context.violationMessage());
        }
        text("digits success with %s and %s", str, num);
    }

    @GetAction("/max")
    public void max(@Max(100) int max) {
        if (context.hasViolation()) {
            text("Error(s): \n%s", context.violationMessage());
        }
        text("max success with %s", max);
    }

    public static void main(String[] args) throws Exception {
        RunApp.start(ValidationDemoApp.class);
    }

}
