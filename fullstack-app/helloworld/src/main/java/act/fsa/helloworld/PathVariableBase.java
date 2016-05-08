package act.fsa.helloworld;

import act.cli.Optional;
import act.controller.Controller;
import act.di.OptionalPathVariable;
import act.di.PathVariable;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.util.S;

@Controller("/path_var")
public class PathVariableBase extends Controller.Util {

    @PathVariable
    protected String foo;

    @PathVariable("bar")
    protected int id;

    @OptionalPathVariable
    protected String x;

    @PathVariable(optional = true)
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
