package act.fsa.views;

import act.boot.app.RunApp;
import act.controller.Controller;
import act.inject.param.NoBind;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.result.Result;

@SuppressWarnings("unused")
public class ViewsDemo extends Controller.Util {

    @NoBind
    private String title = "ActFramework View Demo";
    private String who = "ActFramework";

    @GetAction({"", "rythm"})
    public void home() {
        render(title, who);
    }

    @GetAction("beetl")
    public void beetl() {
        render(title, who);
    }

    @GetAction("velocity")
    public void velocity() {
        throw renderTemplate(title, who);
    }

    @GetAction("freemarker")
    public Result freemarker() {
        return renderTemplate(title, who);
    }

    @GetAction("/api/v1/greeting/{who}")
    public String helloTo() {
        return "hello " + who;
    }

    public static void main(String[] args) throws Exception {
        RunApp.start(ViewsDemo.class);
    }

}
