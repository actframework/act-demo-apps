package demo.helloworld;

import act.controller.annotation.UrlContext;
import act.util.CacheFor;
import org.osgl.mvc.annotation.GetAction;

@UrlContext("192")
public class GH192 extends GHTest {

    @GetAction
    public void test() {
        throw new NullPointerException();
    }

    @GetAction("home")
    @CacheFor
    public String home() {
        return "home";
    }

}
