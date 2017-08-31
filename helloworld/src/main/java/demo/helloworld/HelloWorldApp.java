package demo.helloworld;

import act.Act;
import act.app.App;
import act.inject.DefaultValue;
import act.job.OnAppStart;
import act.util.Output;
import org.osgl.mvc.annotation.GetAction;

/**
 * The simple hello world app.
 * <p>Run this app, try to update some of the code, then
 * press F5 in the browser to watch the immediate change
 * in the browser!</p>
 */
@SuppressWarnings("unused")
public class HelloWorldApp {

    @GetAction
    public void home(@DefaultValue("World") @Output String who) {
    }

    @OnAppStart
    public static void printId(App app) {
        System.out.println(app.id());
    }

    public static void main(String[] args) throws Exception {
        Act.start();
    }

}
