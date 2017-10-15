package demo.helloworld;

import act.controller.Controller;
import act.controller.annotation.TemplateContext;
import act.controller.annotation.UrlContext;
import org.osgl.mvc.annotation.PostAction;

@UrlContext("/gh")
@TemplateContext("/gh")
public class GHTest extends Controller.Util {


    @PostAction("foo")
    public Foo create(Foo foo) {
        return foo;
    }

}
