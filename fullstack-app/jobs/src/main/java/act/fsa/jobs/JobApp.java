package act.fsa.jobs;

import act.app.AppContext;
import act.boot.app.RunApp;
import act.conf.AppConfig;
import act.job.OnAppStart;
import org.osgl.http.H;
import org.osgl.logging.L;
import org.osgl.logging.Logger;
import org.osgl.mvc.annotation.Before;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.result.Result;
import static act.controller.Controller.Util.*;

/**
 * The simple hello world app.
 * <p>Run this app, try to update some of the code, then
 * press F5 in the browser to watch the immediate change
 * in the browser!</p>
 */
public class JobApp {

    private static final Logger logger = L.get(JobApp.class);

    public JobApp() {
        //logger.info(new RuntimeException(), "JobApp initialized");
    }

    @Before
    public void mockFormat(String fmt, AppContext context) {
        if ("json".equals(fmt)) {
            context.accept(H.Format.json);
        }
        context.session().put("foo", "bar");
    }

    @GetAction("/hello")
    public String sayHello(AppContext context) {
        context.app().jobManager().start(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world!");
            }
        });
        return "Hello Ying!";
    }

    @GetAction("/bye")
    public void byePlayAndSpring() {
        text("bye Play and Spring!!");
    }

    @GetAction("/greeting")
    public void greeting(String who, int age) {
        render(who, age);
    }

    @GetAction("/thank")
    public static String thankYou() {
        return "thank you!";
    }

    public static void main(String[] args) throws Exception {
        RunApp.start(JobApp.class);
    }

    @OnAppStart(async = true)
    public void onAppStartAsync() {
        logger.info("onAppStartAsync called");
    }

    @OnAppStart
    public static void onAppStartSync() {
        logger.info("onAppStartSync called");
    }
}
