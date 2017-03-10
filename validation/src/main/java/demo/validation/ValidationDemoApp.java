package demo.validation;

import act.Act;
import act.app.ActionContext;
import act.controller.Controller;
import act.validation.Email;
import org.osgl.$;
import org.osgl.http.H;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.result.Result;
import org.osgl.util.S;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static act.controller.Controller.Util.render;
import static act.controller.Controller.Util.renderText;
import static act.controller.Controller.Util.text;

/**
 * The simple hello world app.
 * <p>Run this app, try to update some of the code, then
 * press F5 in the browser to watch the immediate change
 * in the browser!</p>
 */
@SuppressWarnings("unused")
public class ValidationDemoApp {

    @Inject
    private ActionContext context;

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
            renderText("Error(s): \n%s", context.violationMessage());
        }
        renderText("size success with %s and %s", text, $.toString2(list));
    }

    @GetAction("/digits")
    public void digits(@Digits(integer = 4, fraction = 2) String str, ActionContext context, @Digits(integer = 3, fraction = 0) Integer num) {
        if (context.hasViolation()) {
            renderText("Error(s): \n%s", context.violationMessage());
        }
        renderText("digits success with %s and %s", str, num);
    }

    @GetAction("/max")
    public void max(@Max(value = 100, message = "foo.bar") int max) {
        if (context.hasViolation()) {
            renderText("Error(s): \n%s", context.violationMessage());
        }
        renderText("max success with %s", max);
    }

    @GetAction("/email")
    public void email(@Email String email) {
        if (context.hasViolation()) {
            renderText("Error(s): \n%s", context.violationMessage());
        }
        renderText("max success with %s", email);
    }

    public static class Foo {
        @NotNull
        public String name;
        public int num;

        @Valid
        public Bar bar;

        @Override
        public String toString() {
            return S.concat("name:", name, "; num:", S.string(num));
        }
    }

    public static class Bar {
        @Max(100)
        public int num;
    }

    @GetAction("foo")
    public void foo(@Valid Foo x) {
        if (context.hasViolation()) {
            renderText("Error(s): \n%s", context.violationMessage());
        }
        renderText("POJO validate success with %s", x);
    }

    @Controller("sf")
    public static class StatefulController {

        @Valid
        Foo x;

        @Inject
        ActionContext context;

        @GetAction("foo")
        public void foo() {
            if (context.hasViolation()) {
                renderText("Error(s): \n%s", context.violationMessage());
            }
            renderText("POJO validate success with %s", x);
        }

    }

    public static void main(String[] args) throws Exception {
        Act.start("Validation Demo");
    }

}
