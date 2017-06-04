package demo.helloworld;

import act.Act;
import act.controller.annotation.TemplateContext;
import act.inject.DefaultValue;
import act.inject.param.NoBind;
import act.util.CacheFor;
import act.util.Global;
import act.util.Output;
import act.view.ProvidesImplicitTemplateVariable;
import org.osgl.http.H;
import org.osgl.mvc.annotation.*;
import org.osgl.util.N;
import org.osgl.util.S;

import java.math.BigDecimal;

import static act.controller.Controller.Util.render;

@SuppressWarnings("unused")
@TemplateContext("hello")
public class HelloWorldApp {

    @ProvidesImplicitTemplateVariable("foo")
    public String foo() {
        return "bar";
    }

    @ProvidesImplicitTemplateVariable("bar")
    public static int bar() {
        return 3;
    }

    @ProvidesImplicitTemplateVariable("reqUrl")
    public static String url(H.Request request) {
        return request.url();
    }

    @GetAction("/int/{<[0-9]+>n}")
    public int n(int n) {
        return n;
    }

    @PostAction("/post")
    public int post(int n) {
        return n;
    }

    @PutAction("/put")
    public int put(int n) {
        return n;
    }

    @PatchAction("/patch")
    public int patch(int n) {
        return n;
    }

    @DeleteAction("/delete")
    public String delete() {
        return "deleted";
    }

    @GetAction("/cache")
    @CacheFor
    public String testCache() {
        return S.random(5);
    }

    @GetAction("/xlogin")
    public String xlogin() {
        return "xlogin";
    }

    @DeleteAction
    public void deleteTest() {
    }

    @PostAction
    public void postTest() {}

    @GetAction
    @CacheFor(5)
    public void home(@DefaultValue("world") @Output String who, H.Response response) {
        response.contentType(H.Format.HTML.contentType());
        response.characterEncoding("UTF-8");
    }

    @GetAction("/x")
    public void x() {
        render("index");
    }

    @GetAction("y")
    public void y() {
        render("/yes");
    }

    @GetAction("/z")
    @TemplateContext("wow")
    public void z() {
        render();
    }

    @Output
    private String x = "X-" + S.random(3);

    @Output("yy")
    @NoBind
    private int y = 5 + N.randInt(100);


    @Global
    @Before
    public void injectorA() {
        System.out.println("injector A");
    }

    @Before
    @Global
    public void logUrl(H.Request req) {
        Act.LOGGER.info("url: %s", req.fullUrl());
    }

    public static void main(String[] args) throws Exception {
        //Act.start("OS China");
        BigDecimal a = new BigDecimal(3.3);
        System.out.println(a);
        System.out.println(a.subtract(BigDecimal.ONE));
        System.out.println(a.add(BigDecimal.ONE.negate()));
    }

}
