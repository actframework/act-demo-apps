package act.fsa.helloworld;

import act.boot.app.RunApp;
import act.inject.HeaderVariable;
import act.util.IdGenerator;
import act.view.ActServerError;
import org.osgl.$;
import org.osgl.http.H;
import org.osgl.http.Http;
import org.osgl.mvc.annotation.*;
import org.osgl.mvc.result.ErrorResult;
import org.osgl.mvc.result.Result;
import org.osgl.util.C;
import org.osgl.util.E;
import org.osgl.util.S;

import java.io.File;
import java.util.List;

import static act.controller.Controller.Util.*;

/**
 * The simple hello world app.
 * <p>Run this app, try to update some of the code, then
 * press F5 in the browser to watch the immediate change
 * in the browser!</p>
 */
@With(MyFilter.class)
public class HelloWorldApp {

    @SessionFree
    @GetAction
    public void home() {
    }

    @GetAction("/dyna/{s1}/s1")
    public String dyna1(String s1) {
        return s1;
    }

    @GetAction("/dyna/{s2}/s2")
    public String dyna2(String s2) {
        return s2;
    }

    @SessionFree
    @GetAction({"/hello", "/hi", "/nihao"})
    public String sayHello() {
        return "Hello Act!";
    }

    @SessionFree
    @GetAction("hiTo")
    public void sayHelloTo(String fn, String ln) {
        Person person = new Person(fn, ln);
        render(person, ln);
    }

    @SessionFree
    @GetAction("/person")
    public Person person(String firstName, String lastName) {
        if (S.allBlank(firstName, lastName)) {
            return null;
        }
        return new Person(firstName, lastName);
    }

    @Action(value = "/sl", methods = {H.Method.GET, H.Method.POST})
    public List<String> takeStringList(List<String> list) {
        return list;
    }

    @Action(value = "/ca", methods = {H.Method.GET, H.Method.POST})
    public String takeCharArray(char[] ca) {
        return new String(ca);
    }

    @SessionFree
    @GetAction("/bye")
    public void byePlayAndSpring() {
        text("bye Play and Spring!!");
    }

    @GetAction("/greeting")
    public void greeting(String who, int age) {
        render(who, age);
    }

    @SessionFree
    @GetAction("/int")
    public int testPrimitiveBinding(int value) {
        return value;
    }

    @SessionFree
    @GetAction("/status/{status}")
    public void testStatus(int status) {
        if (status < 400) {
            throw new ErrorResult(Http.status(status));
        }
        badRequestIf(400 == status);
        unauthorizedIf(401 == status);
        forbiddenIf(403 == status);
        notFoundIf(404 == status);
        conflictIf(409 == status);
        throw ActServerError.of(status);
    }

    @GetAction("/dir")
    public List<String> dir() {
        File file = new File(".");
        return C.listOf(file.list());
    }

    @GetAction("/download")
    public Result downloadFile(String path) {
        E.illegalArgumentIf(path.contains(".."));
        File file = new File(path);
        return download(file);
    }


    @GetAction("/product/{catalog}/{prod}/price")
    public Result price(String catalog, String prod) {
        int n = $.random(C.range(100, 400));
        String price = n + ".99";
        System.out.println("aaa");
        return render(catalog, prod, price);
    }

    @GetAction("/this/might/trigger/not/found/error")
    public void might404(String id) {
        notFoundIf(S.blank(id));
        redirect("/");
    }

    @GetAction("/this/will/trigger/internal/error")
    public void internalError() {
        throw new NullPointerException();
    }

    @GetAction("/this/will/trigger/rythm/error")
    public Result rythmError() {
        return render();
    }

    @GetAction("/this/will/trigger/permission/denied/error")
    public Result noAccess() {
        return forbidden();
    }

    @GetAction("/exception")
    public void javaException(String ex) throws Exception {
        Exception exception = $.newInstance(ex);
        throw exception;
    }

    public static void main(String[] args) throws Exception {
        RunApp.start(HelloWorldApp.class);
        //System.out.println(IdGenerator.SAFE_ENCODER.longToStr(Integer.MAX_VALUE));
    }

}
