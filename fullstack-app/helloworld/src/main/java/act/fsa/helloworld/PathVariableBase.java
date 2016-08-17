package act.fsa.helloworld;

import act.controller.Controller;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.util.S;

@Controller("/path_var")
public class PathVariableBase extends Controller.Util {

    protected String foo;

    protected int id;

    protected String x;

    protected String y;


    @GetAction("{foo}/{bar}")
    public String fooBar() {
        return S.times(foo, id);
    }

    @GetAction("{foo}/{bar}/{x}/{y}")
    public String fooBarXY() {
        return fooBar() + x + y;
    }
}
