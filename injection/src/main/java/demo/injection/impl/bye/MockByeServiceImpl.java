package demo.injection.impl.bye;

import act.app.ActionContext;
import demo.injection.ByeService;
import org.osgl.util.E;
import org.osgl.util.S;

import javax.inject.Inject;

public class MockByeServiceImpl implements ByeService {

    private ActionContext context;

    @Inject
    public MockByeServiceImpl(ActionContext context) {
        E.NPE(context);
        this.context = context;
    }

    @Override
    public String bye(String who) {
        return S.fmt("mock bye %s!", who);
    }

}
