package act.fsa.views;

import act.Act;
import act.boot.app.RunApp;
import act.controller.Controller;
import act.inject.param.NoBind;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.result.Result;

@SuppressWarnings("unused")
public class ViewsDemo extends Controller.Util {

    @NoBind
    private String title = "ActFramework View Demo";
    private String who = "ActFramework";

    @PostAction("/foo")
    public byte foo(byte b) {
        return b;
    }

    @GetAction("e500")
    public static String backendServerError() {
        // this will trigger a runtime error in the backend
        return Act.crypto().decrypt("bad-crypted-msg");
    }

    @GetAction({"", "rythm"})
    public void rythm() {
        render(title, who);
    }

    @GetAction("rythm/error")
    public void rythmTemplateError() {
    }

    @GetAction("rythm/error/runtime")
    public void rythmTemplateRuntimeError() {}

    @GetAction("beetl")
    public void beetl() {
        render(title, who);
    }

    @GetAction("beetl/error")
    public void beetlTemplateError() {
    }

    @GetAction("velocity")
    public void velocity() {
        throw renderTemplate(title, who);
    }

    @GetAction("velocity/error")
    public void velocityTemplateError() {
    }

    @GetAction("freemarker")
    public Result freemarker() {
        return renderTemplate(title, who);
    }

    @GetAction("freemarker/error")
    public void freemarkerTemplateError() {
    }

    @GetAction("freemarker/error/runtime")
    public void freemarkerTemplateRuntimeError() {
        ViewsDemo demo = new ViewsDemo();
        renderTemplate(demo);
    }

    @GetAction("/api/v1/greeting/{who}")
    public String helloTo() {
        return "hello " + who;
    }

    public static String rt() {
        return backendServerError();
    }

    public static void main(String[] args) throws Exception {
        RunApp.start(ViewsDemo.class);
    }

}
