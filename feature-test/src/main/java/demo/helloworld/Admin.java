package demo.helloworld;

import act.cli.Command;
import act.controller.annotation.UrlContext;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.util.S;

@UrlContext("admin")
@SuppressWarnings("unused")
public class Admin {

    @GetAction("random")
    public String random() {
        return S.random();
    }

    @Command("gh322")
    public static void cliHandlerWithException() {
        throw new IllegalStateException("github issue 322");
    }

}
