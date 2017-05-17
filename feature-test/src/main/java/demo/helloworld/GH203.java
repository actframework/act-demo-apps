package demo.helloworld;

import act.controller.annotation.UrlContext;
import demo.helloworld.gh203.PostOffice;
import org.osgl.mvc.annotation.GetAction;

import javax.inject.Inject;

@UrlContext("203")
public class GH203 extends GHTest {

    @Inject
    private PostOffice postOffice;

    @GetAction
    public void gh203() {
        postOffice.sendEmail();
        ok();
    }

}
