package demo.helloworld;

import act.controller.annotation.TemplateContext;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.util.IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static act.controller.Controller.Util.render;

@TemplateContext("/gh")
public class GH158 {

    @GetAction("/login")
    public String login() {
        return "login";
    }

    @GetAction("gh158")
    public final void gh158() throws IOException {
        try (InputStream is = new FileInputStream("pom.xml")) {
            String pom = IO.readContentAsString(is);
            throw render(pom);
        }
    }

}
