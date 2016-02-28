package act.doc.sample;

import act.app.App;
import act.boot.app.RunApp;
import act.controller.Controller;
import act.di.Context;
import act.job.AppJobManager;
import org.osgl.mvc.annotation.GetAction;

import java.util.concurrent.Callable;

public class DocSampleApp {

    @GetAction
    public void home(@Context AppJobManager jobManager) {
        String engine = "rythm";
        jobManager.now(new Runnable() {
            @Override
            public void run() {
                System.out.println("home entry invoked");
            }
        });
        jobManager.delay(new Runnable() {
            @Override
            public void run() {
                System.out.println("delayed log");
            }
        }, "5s");
        Controller.Util.render(engine);
    }

    @GetAction("/fm")
    public void homeFreemarker() {
        String engine = "freemarker";
        Controller.Util.render(engine);
    }

    @GetAction("/vt")
    public void homeVelocity() {
        String engine = "velocity";
        Controller.Util.render(engine);
    }

    public static void main(String[] args) throws Exception {
        RunApp.start(DocSampleApp.class);
    }
}
