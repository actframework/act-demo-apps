package demo.injection;

import act.Act;
import org.osgl.mvc.annotation.Action;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.With;
import org.osgl.mvc.result.Result;

import javax.inject.Inject;

import static act.controller.Controller.Util.render;
import static act.controller.Controller.Util.text;

@SuppressWarnings("unused")
@With(SomeOtherClass.class)
public class InjectionApp {

    @Inject
    private HiService hi;

    @Inject
    private ByeService bye;

    @GetAction
    public void home() {
    }

    @GetAction("/greeting")
    public String greeting(GreetingService greeting) {
        return greeting.greeting();
    }

    @Action("/hi")
    public void hi(String who) {
        String message = hi.sayHi(who);
        render(message);
    }

    @Action("/bye")
    public void bye(String who) {
        text(bye.bye(who));
    }

    public static void main(String[] args) throws Exception {
        Act.start();
    }

}
