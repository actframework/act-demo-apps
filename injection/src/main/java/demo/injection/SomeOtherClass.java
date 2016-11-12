package demo.injection;

import act.app.ActionContext;
import org.osgl.http.H;
import org.osgl.mvc.annotation.Before;

public class SomeOtherClass {

    @Before(only = "greeting")
    public void mockFormat(String fmt, ActionContext context) {
        if ("json".equals(fmt)) {
            context.accept(H.Format.TXT);
        }
    }

}
