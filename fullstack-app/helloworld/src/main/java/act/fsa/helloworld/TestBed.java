package act.fsa.helloworld;

import act.controller.Controller;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.util.E;

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

}
