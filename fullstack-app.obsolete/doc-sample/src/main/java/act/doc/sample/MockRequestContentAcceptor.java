package act.doc.sample;

import act.app.ActionContext;
import act.handler.builtin.controller.BeforeInterceptor;
import act.plugin.Plugin;
import org.osgl.http.H;
import org.osgl.mvc.result.Result;

import javax.inject.Singleton;

@Singleton
public class MockRequestContentAcceptor extends BeforeInterceptor {

    public MockRequestContentAcceptor() {
        super(1);
        Plugin.InfoRepo.register(this);
    }

    @Override
    public Result handle(ActionContext actionContext) throws Exception {
        String s = actionContext.paramVal("fmt");
        if ("json".equalsIgnoreCase(s)) {
            actionContext.accept(H.Format.JSON);
        } else if ("csv".equalsIgnoreCase(s)) {
            actionContext.accept(H.Format.CSV);
        } else if ("xml".equalsIgnoreCase(s)) {
            actionContext.accept(H.Format.XML);
        }
        return null;
    }
}
