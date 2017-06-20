package demo.helloworld;

import act.Act;
import act.inject.DefaultValue;
import act.util.Output;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.util.E;

/**
 * The simple hello world app.
 * <p>Run this app, try to update some of the code, then
 * press F5 in the browser to watch the immediate change
 * in the browser!</p>
 */
@SuppressWarnings("unused")
public class HelloWorldApp {

    @GetAction
    public void home(@DefaultValue("world") @Output String who) {
    }

    @GetAction("/error")
    public void throwError() {
        throw E.tbd();
    }

    @GetAction("/echo2")
    public String echo(String message) {
        return message;
    }

    public static void main(String[] args) throws Exception {
        Act.start("Hello World");
    }

}
