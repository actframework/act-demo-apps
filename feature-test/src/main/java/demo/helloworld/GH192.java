package demo.helloworld;

import act.util.CacheFor;
import org.osgl.mvc.annotation.GetAction;

public class GH192 extends GHTest {

    @GetAction("192")
    public void test() {
        throw new NullPointerException();
    }

    @GetAction("home")
    @CacheFor
    public String home() {
        return "home";
    }

}
