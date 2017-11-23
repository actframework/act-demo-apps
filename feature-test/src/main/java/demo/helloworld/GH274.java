package demo.helloworld;

import act.controller.annotation.UrlContext;
import org.osgl.mvc.annotation.GetAction;

@UrlContext("274")
public class GH274 extends GHTest {

    @GetAction
    public void gh274() {
        badRequest();
    }

}
