package act.fsa.helloworld;

import act.controller.Controller;
import act.security.CSRF;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;

@Controller("/csrf")
@CSRF.Enable
public class CSRFDemo extends Controller.Util {

    @GetAction
    public void form() {
    }

    @PostAction
    public void post(String msg) {
        render("/act/fsa/helloworld/CSRFDemo/form.html", msg);
    }

}
