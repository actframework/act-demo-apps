package demo.helloworld;

import org.osgl.mvc.annotation.GetAction;
import org.osgl.util.IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static act.controller.Controller.Util.render;

public class GH158 extends GHTest {

    @GetAction("/login")
    public String login() {
        return "login";
    }

    @GetAction("158")
    public final void gh158() throws IOException {
        try (InputStream is = new FileInputStream("pom.xml")) {
            String pom = IO.readContentAsString(is);
            throw render(pom);
        }
    }

}
