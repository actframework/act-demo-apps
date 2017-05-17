package demo.helloworld;

import act.controller.annotation.UrlContext;
import act.util.Output;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.util.S;

@UrlContext("202")
public class GH202  extends GHTest {

    @Output
    private String randomStr = S.random();

    @GetAction
    public void gh202() {
        badRequest("reason: %s", "莫须有");
    }

}
