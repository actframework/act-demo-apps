package act.fsa.helloworld;

import act.app.App;
import act.conf.AppConfig;
import act.controller.Controller;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.util.E;
import org.osgl.util.S;

@Controller("/testbed")
public class TestBed {

    @GetAction
    public String entry() {
        return "Welcome to the wonder world!";
    }

    @GetAction("/CustomResourceConflictException")
    public void testExceptionWithResponseStatus() throws Exception {
        throw new ResourceConflictException();
    }

    @GetAction("/illegalArgumentException")
    public void testIllegalArgumentException() {
        throw new IllegalArgumentException();
    }

    @GetAction("/unsupportException")
    public void testUnsupportedException() {
        throw E.tbd("TBD: blah blah...");
    }

    @GetAction("/illegalStateException")
    public void testIllegalStateException() {
        throw new IllegalStateException();
    }

    @PostAction("/ctx_param_cnt")
    public String testContextParamCount(Person person, int n, App app, AppConfig config) {
        return S.builder().append(person.getFirstName()).append(":").append(app.cuid()).append(":").append(config.httpPort()).toString();
    }

}
