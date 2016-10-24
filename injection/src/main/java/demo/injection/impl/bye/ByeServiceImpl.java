package demo.injection.impl.bye;

import act.app.ActionContext;
import demo.injection.ByeService;
import org.osgl.$;
import org.osgl.util.S;

import javax.inject.Inject;

public class ByeServiceImpl implements ByeService {

    private ActionContext context;

    @Inject
    public ByeServiceImpl(ActionContext context) {
        this.context = $.notNull(context);
    }

    @Override
    public String bye(String who) {
        return S.fmt("In answering requesting sent to [%s], we say bye %s!", context.req().url(), who);
    }
}
