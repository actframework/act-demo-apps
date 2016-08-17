package act.fsa.helloworld;

import act.app.ActionContext;
import org.osgl.http.H;
import org.osgl.mvc.annotation.Before;
import org.osgl.mvc.annotation.SessionFree;

/**
 * Created by luog on 12/11/15.
 */
public class MyFilter {
    @SessionFree
    @Before
    public void mockFormat(String fmt, ActionContext context) {
        if ("json".equals(fmt)) {
            context.accept(H.Format.JSON);
        }
    }
}
