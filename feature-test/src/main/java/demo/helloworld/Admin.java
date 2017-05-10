package demo.helloworld;

import act.controller.annotation.UrlContext;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.util.S;

@UrlContext("admin")
public class Admin {

    @GetAction("random")
    public String random() {
        return S.random();
    }

}
