package demo.helloworld;

import act.controller.annotation.UrlContext;
import org.osgl.mvc.annotation.GetAction;

@UrlContext("210")
public class GH210 extends GHTest {

    @GetAction
    public void gh210() {
    }

}
