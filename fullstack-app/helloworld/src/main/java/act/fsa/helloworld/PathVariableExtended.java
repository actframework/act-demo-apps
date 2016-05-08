package act.fsa.helloworld;

import org.osgl.mvc.annotation.GetAction;

public class PathVariableExtended extends PathVariableBase {

    @GetAction("2/{foo}/{bar}")
    public String fooBar() {
        return foo + id;
    }
}
