package act.fsa.helloworld;

import act.app.ActionContext;
import act.boot.app.RunApp;
import act.view.ActForbidden;
import org.osgl.$;
import org.osgl.http.H;
import org.osgl.mvc.annotation.Before;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.With;
import org.osgl.mvc.result.NotModified;
import org.osgl.mvc.result.Result;
import org.osgl.util.C;
import org.osgl.util.S;

import java.util.Objects;

import static act.controller.Controller.Util.*;

/**
 * The simple hello world app.
 * <p>Run this app, try to update some of the code, then
 * press F5 in the browser to watch the immediate change
 * in the browser!</p>
 */
@With(MyFilter.class)
public class HelloWorldApp {


    @GetAction("/")
    public Result home() {
        return render();
    }

    @GetAction({"/hello", "/hi", "/nihao"})
    public String sayHello() {
        return "Hello Act!";
    }

    @GetAction("hiTo")
    public void sayHelloTo(String fn, String ln) {
        Person person = new Person(fn, ln);
        render(person, ln);
    }

    @GetAction("/person")
    public Person person(String firstName, String lastName) {
        return new Person(firstName, lastName);
    }

    @GetAction("/bye")
    public void byePlayAndSpring() {
        text("bye Play and Spring!!");
    }

    @GetAction("/greeting")
    public void greeting(String who, int age) {
        render(who, age);
    }

    @GetAction("/product/{catalog}/{prod}/price")
    public Result price(String catalog, String prod) {
        int n = $.random(C.range(100, 400));
        String price = n + ".99";
        return render(catalog, prod, price);
    }

    @GetAction("/this/might/trigger/not/found/error")
    public void might404(String id) {
        notFoundIf(S.blank(id));
        redirect("/");
    }

    @GetAction("/this/will/trigger/internal/error")
    public void internalError() {
        throw new NullPointerException();
    }

    @GetAction("/this/will/trigger/rythm/error")
    public Result rythmError() {
        return render();
    }

    @GetAction("/this/will/trigger/permission/denied/error")
    public Result noAccess() {
        return forbidden();
    }

    public static void main(String[] args) throws Exception {
        RunApp.start(HelloWorldApp.class);
    }

}
