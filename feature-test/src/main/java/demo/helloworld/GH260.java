package demo.helloworld;

import act.controller.annotation.UrlContext;
import act.util.DisableFastJsonCircularReferenceDetect;
import demo.helloworld.gh251.Foo;
import org.osgl.mvc.annotation.GetAction;

@UrlContext("260")
public class GH260 extends GHTest {


    @DisableFastJsonCircularReferenceDetect
    @GetAction
    public Foo foo() {
        return new Foo();
    }

}
