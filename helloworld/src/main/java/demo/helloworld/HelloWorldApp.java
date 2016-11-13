package demo.helloworld;

import act.Version;
import act.boot.app.RunApp;
import org.osgl.http.H;
import org.osgl.logging.LogManager;
import org.osgl.logging.Logger;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.util.C;
import org.osgl.util.S;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * The simple hello world app.
 * <p>Run this app, try to update some of the code, then
 * press F5 in the browser to watch the immediate change
 * in the browser!</p>
 */
@SuppressWarnings("unused")
@Singleton
public class HelloWorldApp {

    public static final Logger LOGGER = LogManager.get("demo.helloworld");

    @GetAction
    public void home() {
        LOGGER.info("hello world");
    }

    @Inject
    private H.Response resp;

    public static void main(String[] args) throws Exception {
        RunApp.start("Hello World", Version.appVersion(), "demo");
    }

}
