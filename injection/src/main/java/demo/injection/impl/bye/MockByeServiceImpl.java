package demo.injection.impl.bye;

import act.app.ActionContext;
import demo.injection.ByeService;
import org.osgl.$;
import org.osgl.util.E;
import org.osgl.util.S;

import javax.inject.Inject;

public class MockByeServiceImpl implements ByeService {

    private ActionContext context;

    @Inject
    public MockByeServiceImpl(ActionContext context) {
        this.context = $.notNull(context);
    }

    @Override
    public String bye(String who) {
        return S.fmt("In answering requesting sent from [%s], we say bye %s! (in DEV mode)", context.req().ip(), who);
    }

}
