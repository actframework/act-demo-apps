package demo.helloworld;

import act.controller.annotation.UrlContext;
import act.util.Global;
import org.osgl.http.H;
import org.osgl.mvc.annotation.Before;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.util.S;

import static act.controller.Controller.Util.redirect;

@UrlContext("admin")
public class Admin {

    @Before(except = {".*login"})
    @Global
    public void ensureUser(H.Session session) {
        if (!session.containsKey("username")) {
            redirect("/login");
        }
    }

    @GetAction("random")
    public String random() {
        return S.random();
    }

}
