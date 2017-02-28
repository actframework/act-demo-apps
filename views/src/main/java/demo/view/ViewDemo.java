package demo.view;

import act.Act;
import act.controller.Controller;
import act.inject.param.NoBind;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.result.Result;

@SuppressWarnings("unused")
public class ViewDemo extends Controller.Util {

    @NoBind
    private String title = "ActFramework View Demo";
    private String who = "ActFramework";

    @GetAction("e500")
    public static String backendServerError() {
        // this will trigger a runtime error in the backend
        return Act.crypto().decrypt("bad-crypted-msg");
    }

    @PostAction("/foo")
    public byte foo(byte b) {
        return b;
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

    @GetAction("beetl/error/runtime")
    public void beetlTemplateRuntimeError() {}

    @GetAction("velocity")
    public void velocity() {
        throw template(title, who);
    }

    @GetAction("velocity/error")
    public void velocityTemplateError() {
    }

    @GetAction("velocity/error/runtime")
    public void velocityTemplateRuntimeError() {
        Class<ViewDemo> demo = ViewDemo.class;
        template(demo);
    }

    @GetAction("freemarker")
    public Result freemarker() {
        return template(title, who);
    }

    @GetAction("freemarker/error")
    public void freemarkerTemplateError() {
    }

    @GetAction("freemarker/error/runtime")
    public void freemarkerTemplateRuntimeError() {
        ViewDemo demo = new ViewDemo();
        template(demo);
    }

    @GetAction("mustache")
    public void mustache() {
        String appName = Act.app().name();
        render(title, who, appName);
    }

    @GetAction("mustache/error")
    public void mustacheTemplateError() {
    }

    @GetAction("mustache/error/runtime")
    public void mustacheTemplateRuntimeError() {
        ViewDemo demo = new ViewDemo();
        template(demo);
    }

    @GetAction("thymeleaf")
    public void thymeleaf() {
        render(title, who);
    }

    @GetAction("thymeleaf/error")
    public void thymeleafTemplateError() {
    }

    @GetAction("thymeleaf/error/runtime")
    public void thymeleafTemplateRuntimeError() {
        ViewDemo demo = new ViewDemo();
        template(demo);
    }

    @GetAction("/api/v1/greeting/{who}")
    public String helloTo() {
        return "hello " + who;
    }

    public static String rt() {
        return backendServerError();
    }

    public static void main(String[] args) throws Exception {
        Act.start("View Demo");
    }

}
