package act.fsa.auto_obj;

import act.boot.app.RunApp;
import act.controller.Controller;
import org.osgl.$;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.result.Result;

public class AutoObjectApp extends Controller.Util {

    private static Foo foo1 = new Foo();
    private static Foo foo2 = new Foo();

    @GetAction("/")
    public Result home() {
        return render();
    }

    @GetAction("/foo1")
    public Foo getFoo1() {
        return foo1;
    }

    @GetAction("/foo2")
    public Foo getFoo2() {
        return foo2;
    }

    @GetAction("/foo1/hc")
    public int hashCode1() {
        return foo1.hashCode();
    }

    @GetAction("/foo2/hc")
    public int hashCode2() {
        return foo2.hashCode();
    }

    @PostAction("/foo1")
    public Result updateFoo1(Foo foo) {
        foo1 = $.NPE(foo);
        return ok();
    }

    @PostAction("/foo2")
    public Result updateFoo2(Foo foo) {
        foo2 = $.NPE(foo);
        return ok();
    }

    @GetAction("/eq")
    public boolean equals() {
        return foo1.equals(foo2);
    }

    public static void main(String[] args) throws Exception {
        RunApp.start(AutoObjectApp.class);
    }

}
