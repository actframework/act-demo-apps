package act.fsa.auto_obj;

import act.boot.app.RunApp;
import act.cli.Command;
import act.cli.TableView;
import act.util.PropertySpec;
import org.osgl.$;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.result.Result;
import org.osgl.util.C;

import java.util.List;

import static act.controller.Controller.Util.*;

public class AutoObjectApp {

    private static Foo foo1 = new Foo();
    private static Foo foo2 = new Foo();

    @GetAction("/")
    public Result home() {
        return render();
    }

    @GetAction("/foo")
    public List<Foo> listFoo() {
        return C.list(foo1, foo2);
    }

    @Command("foo.show")
    @GetAction("/foo/{id}")
    @PropertySpec(Foo.DETAIL_VIEW)
    @TableView
    public Foo getFoo(int id) {
        return dispatch(id, $.F.<Foo>identity());
    }

    @GetAction("/foo/{id}/hc")
    public int getHashCode(int id) {
        return dispatch(id, $.F.<Foo>hc());
    }

    @PostAction("/foo/{id}")
    public Result updateFoo(int id, Foo foo) {
        if (null == foo) {
            return badRequest();
        }
        switch (id) {
            case 1:
                foo1 = foo;
                break;
            case 2:
                foo2 = foo;
                break;
            default:
                System.out.println("unknown id: " + id);
                throw notFound();
        }
        return ok();
    }

    @GetAction("/eq")
    public boolean isEquals() {
        return foo1.equals(foo2);
    }

    private <T> T dispatch(int id, $.Function<Foo, T> extractor) {
        switch (id) {
            case 1:
                return extractor.apply(foo1);
            case 2:
                return extractor.apply(foo2);
            default:
                System.out.println("unknown id: " + id);
                throw notFound();
        }
    }

    public static void main(String[] args) throws Exception {
        RunApp.start(AutoObjectApp.class);
    }

}
