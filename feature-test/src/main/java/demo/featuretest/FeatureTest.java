package demo.featuretest;

/*-
 * #%L
 * actframework Feature Test App
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

import static act.controller.Controller.Util.render;

import act.Act;
import act.apidoc.Description;
import act.cli.Command;
import act.cli.Required;
import act.controller.Controller;
import act.controller.annotation.TemplateContext;
import act.inject.DefaultValue;
import act.inject.param.NoBind;
import act.util.CacheFor;
import act.util.Global;
import act.util.Output;
import act.view.ProvidesImplicitTemplateVariable;
import org.osgl.exception.UnexpectedIOException;
import org.osgl.http.H;
import org.osgl.mvc.annotation.*;
import org.osgl.mvc.result.Redirect;
import org.osgl.mvc.result.Result;
import org.osgl.util.N;
import org.osgl.util.S;

import java.io.File;

@SuppressWarnings("unused")
@TemplateContext("hello")
@With(ExceptionAdvice.class)
public class FeatureTest {

    @Description("E1 endpoint")
    @GetAction("/e1")
    public void triggerException() {
        throw new UnexpectedIOException("x");
    }

    @GetAction("/e2")
    public void triggerException2() throws IllegalAccessException {
        throw new IllegalAccessException("");
    }

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

    @GetAction("/download")
    public void download() {
        File file = new File("/etc/issue");
        Controller.Util.download(file);
    }

    @Action("/redirect/301")
    public Result test301(H.Response response) {
        response.header("Location", "/redirected/301");
        return new Result() {}.status(H.Status.MOVED_PERMANENTLY);
    }

    @GetAction("/redirected/301")
    public String test301Redirected() {
        return 301 + " GET";
    }

    @PostAction("/redirected/301")
    public String test301RedirectedPost() {
        return 301 + " POST";
    }

    @Action("/redirect/302")
    public Result test302(H.Response response) {
        response.header("Location", "/redirected/302");
        return new Result() {}.status(H.Status.FOUND);
    }

    @GetAction("/redirected/302")
    public String test302Redirected() {
        return 302 + " GET";
    }

    @PostAction("/redirected/302")
    public String test302RedirectedPost() {
        return 302 + " POST";
    }

    @Action("/redirect/303")
    public Result test303(H.Response response) {
        response.header("Location", "/redirected/303");
        return new Result() {}.status(H.Status.SEE_OTHER);
    }

    @GetAction("/redirected/303")
    public String test303Redirected() {
        return 303 + " GET";
    }

    @PostAction("/redirected/303")
    public String test303RedirectedPost() {
        return 303 + " POST";
    }

    @Action("/redirect/307")
    public Result test307(H.Request req, H.Response response) {
        response.header("Location", "/redirected/307");
        return new Result() {}.status(H.Status.of(307));
    }

    @GetAction("/redirected/307")
    public String test307GetRedirected() {
        return 307 + " GET";
    }

    @PostAction("/redirected/307")
    public String test307PostRedirected() {
        return 307 + " POST";
    }

    @Action("/redirect/308")
    public Result test308(H.Request req, H.Response response) {
        response.header("Location", "/redirected/308");
        return new Result() {}.status(H.Status.of(308));
    }

    @GetAction("/redirected/308")
    public String test308GetRedirected() {
        return 308 + " GET";
    }

    @PostAction("/redirected/308")
    public String test308PostRedirected() {
        return 308 + " POST";
    }

    @GetAction("/interceptor/exception")
    public String testExceptionInterceptor() {
        throw new XyzExcpetion();
    }

    @Global
    @Before
    public void injectorA() {
        System.out.println("injector A");
    }

    @Command("enc")
    public String encrypt(@Required("specify the message") String msg) {
        return Act.crypto().encrypt(msg);
    }

    @Before
    @Global
    public void logUrl(H.Request req) {
        Act.LOGGER.info("url: %s", req.fullUrl());
    }

    @Action("/see-other")
    public Result seeOther() {
        return Redirect.seeOther("http://google.com");
    }

    @Action("/401")
    public Result unauthorized() {
        return Controller.Util.unauthorized("Access to the staging site");
    }

    @Action("/ia")
    public int[] testArray(int[] ia) {
        return ia;
    }

    public static void main(String[] args) throws Exception {
        Act.start("OS China");
    }

}
