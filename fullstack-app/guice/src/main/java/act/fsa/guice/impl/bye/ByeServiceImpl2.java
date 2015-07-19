package act.fsa.guice.impl.bye;

import act.app.ActionContext;
import act.conf.AppConfig;
import act.fsa.guice.ByeService;
import org.osgl.util.E;
import org.osgl.util.S;

import javax.inject.Inject;

public class ByeServiceImpl2 implements ByeService {

    private ActionContext context;

    @Inject
    public ByeServiceImpl2(ActionContext context) {
        E.NPE(context);
        this.context = context;
    }

    @Override
    public String bye(String who) {
        return S.fmt("zaijian %s!", who);
    }

    public String zaijian() {
        String who = context.paramVal("who");
        return S.fmt("zaijian %s!", who);
    }
}
