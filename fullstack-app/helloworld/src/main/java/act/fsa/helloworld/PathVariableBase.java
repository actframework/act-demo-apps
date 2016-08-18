package act.fsa.helloworld;

import act.controller.Controller;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.result.Result;
import org.osgl.util.S;

@Controller("/path_var")
@SuppressWarnings("unused")
public class PathVariableBase extends Controller.Util {

    protected String foo;

    protected int id;

    protected String x;

    protected String y;

    @GetAction("/path_tst")
    public Result testUrl() {
        return text("non-default");
    }

    @GetAction("/path_tst/{var}")
    public Result testUrl(String var) {
        if (S.blank(var)) {
            var = "default";
        }
        return text(var);
    }


    @GetAction("{foo}/{bar}")
    public String fooBar() {
        return S.times(foo, id);
    }

    @GetAction("{foo}/{bar}/{x}/{y}")
    public String fooBarXY() {
        return fooBar() + x + y;
    }
}
