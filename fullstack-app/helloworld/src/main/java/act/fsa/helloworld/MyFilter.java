package act.fsa.helloworld;

import act.app.ActionContext;
import org.osgl.http.H;
import org.osgl.mvc.annotation.Before;

/**
 * Created by luog on 12/11/15.
 */
public class MyFilter {
    @Before
    public void mockFormat(String fmt, ActionContext context) {
        if ("json".equals(fmt)) {
            context.accept(H.Format.json);
        }
    }
}
