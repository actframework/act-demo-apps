package org.osgl.ochestra.fsa.guice;

import org.osgl.http.H;
import org.osgl.mvc.annotation.Before;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.oms.app.AppContext;

import javax.inject.Inject;

public class DemoController {

    @Before
    public void mockFormat(String fmt, AppContext context) {
        if ("json".equals(fmt)) {
            context.format(H.Format.json);
        }
        context.session().put("foo", "bar");
    }

    @Inject
    DemoService service;

    @GetAction("/greeting")
    public String greeting() {
        return service.greeting();
    }

}
