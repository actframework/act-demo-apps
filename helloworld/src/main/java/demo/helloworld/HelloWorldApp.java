package demo.helloworld;

import act.Version;
import act.boot.app.RunApp;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.Param;
import org.osgl.mvc.annotation.SessionFree;
import org.osgl.mvc.result.Result;

import javax.inject.Inject;
import javax.inject.Singleton;

import static act.controller.Controller.Util.render;

/**
 * The simple hello world app.
 * <p>Run this app, try to update some of the code, then
 * press F5 in the browser to watch the immediate change
 * in the browser!</p>
 */
@SuppressWarnings("unused")
@Singleton
public class HelloWorldApp {

    @GetAction
    @SessionFree
    public Result home(@Param(defVal = "World") String who) {
        return render(who);
    }
    
    @GetAction("/load")
    public String load(LoadManager lm) {
        return lm.payload();
    }

    public static void main(String[] args) throws Exception {
        RunApp.start("HelloWorld", Version.appVersion(), HelloWorldApp.class);
    }

}
