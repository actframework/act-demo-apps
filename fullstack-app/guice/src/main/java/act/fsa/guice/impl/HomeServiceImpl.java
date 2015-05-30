package act.fsa.guice.impl;

import act.controller.Controller;
import act.fsa.guice.HomeService;
import org.osgl.mvc.result.Result;

public class HomeServiceImpl implements HomeService{
    @Override
    public Result welcome() {
        return Controller.Util.render();
    }
}
