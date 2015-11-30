package act.fsa.auto_obj;

import act.boot.app.RunApp;
import org.osgl.$;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.result.Result;

import static act.controller.Controller.Util.notFound;
import static act.controller.Controller.Util.render;

public class AutoObjectApp {

    private static Foo foo1 = new Foo();
    private static Foo foo2 = new Foo();

    @GetAction("/")
    public Result home() {
        return render();
    }

    @GetAction("/foo/{id}")
    public Foo getFoo(int id) {
        return dispatch(id, $.F.<Foo>identity());
    }

    @GetAction("/foo/{id}/hc")
    public int getHashCode(int id) {
        return dispatch(id, $.F.<Foo>hc());
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
