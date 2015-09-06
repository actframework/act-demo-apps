package act.fsa.guice.impl.hi;

import act.controller.Controller;
import act.fsa.guice.HiService;
import org.osgl.mvc.annotation.Action;
import org.osgl.mvc.result.Result;

public class HiServiceImpl implements HiService {
    @Override
    @Action({})
    public Result sayHi(String who) {
        String action = "hi";
        return Controller.Util.render(action, who);
    }
}
