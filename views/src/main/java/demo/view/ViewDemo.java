package demo.view;

/*-
 * #%L
 * actframework app demo - hello world
 * %%
 * Copyright (C) 2018 ActFramework
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import act.Act;
import act.conf.AppConfig;
import act.controller.Controller;
import act.inject.param.NoBind;
import act.view.beetl.BeetlView;
import act.view.freemarker.FreeMarkerView;
import act.view.mustache.MustacheView;
import act.view.rythm.RythmView;
import act.view.thymeleaf.ThymeleafView;
import act.view.velocity.VelocityView;
import org.osgl.$;
import org.osgl.mvc.annotation.Before;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.result.Result;

@SuppressWarnings("unused")
public class ViewDemo extends Controller.Util {

    @NoBind
    private String title = "ActFramework View Demo";
    private String who = "ActFramework";

    @Before
    public static void resetDefaultView(AppConfig config) {
        String templateId = RythmView.ID;
        $.setField("defView", config, Act.viewManager().view(templateId));
    }

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
    public void rythmTemplateRuntimeError() {
    }

    @GetAction("beetl")
    public void beetl() {
        render(title, who);
    }

    @GetAction("beetl/error")
    public void beetlTemplateError() {
    }

    @GetAction("beetl/error/runtime")
    public void beetlTemplateRuntimeError() {
    }

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

    @GetAction("rythm/inline")
    public void rythmInline(AppConfig config) {
        String templateId = RythmView.ID;
        $.setField("defView", config, Act.viewManager().view(templateId));
        render("Hello @who by @templateId", who, templateId);
    }

    @GetAction("beetl/inline")
    public void beetlInline(AppConfig config) {
        String templateId = BeetlView.ID;
        $.setField("defView", config, Act.viewManager().view(templateId));
        render("Hello ${who} by ${templateId}", who, templateId);
    }

    @GetAction("mustache/inline")
    public void mustacheInline(AppConfig config) {
        String templateId = MustacheView.ID;
        $.setField("defView", config, Act.viewManager().view(templateId));
        render("Hello {{who}} by {{templateId}}", who, templateId);
    }

    @GetAction("freemarker/inline")
    public void freemarkerInline(AppConfig config) {
        String templateId = FreeMarkerView.ID;
        $.setField("defView", config, Act.viewManager().view(templateId));
        render("Hello ${who} by ${templateId}", who, templateId);
    }

    @GetAction("thymeleaf/inline")
    public void thymeleafInline(AppConfig config) {
        String templateId = ThymeleafView.ID;
        $.setField("defView", config, Act.viewManager().view(templateId));
        render("<div>Hello <span data-th-text=\"${who}\"></span> by <span data-th-text=\"${templateId}\"></span></div>", who, templateId);
    }

    @GetAction("velocity/inline")
    public void velocityInline(AppConfig config) {
        String templateId = VelocityView.ID;
        $.setField("defView", config, Act.viewManager().view(templateId));
        render("Hello $who by $templateId", who, templateId);
    }

    public static String rt() {
        return backendServerError();
    }

    public static void main(String[] args) throws Exception {
        Act.start("View Demo");
    }

}
