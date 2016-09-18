package act.fsa.views;

import act.boot.app.RunApp;
import act.controller.Controller;
import org.osgl.mvc.annotation.GetAction;

@SuppressWarnings("unused")
public class ViewsDemo extends Controller.Util {

    private String title = "ActFramework View Demo";
    private String who = "ActFramework";

    @GetAction
    public void home() {
        render(title, who);
    }

    @GetAction("beetl")
    public void beetl() {
        render(title, who);
    }

    public static void main(String[] args) throws Exception {
        RunApp.start(ViewsDemo.class);
    }

}
