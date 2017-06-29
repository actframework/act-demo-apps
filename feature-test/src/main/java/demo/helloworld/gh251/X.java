package demo.helloworld.gh251;

import org.osgl.mvc.annotation.GetAction;

import static act.controller.Controller.Util.render;

public class X {

    @GetAction("/no_tmpl")
    public void noTemplate() {
        String s = "s";
        render(s);
    }

    @GetAction("/no_tmpl2")
    public void noTemplate2() {
        String s = "s";
        //return render(s);
        render();
    }


}
