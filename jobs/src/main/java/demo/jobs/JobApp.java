package demo.jobs;

import act.Version;
import act.app.ActionContext;
import act.boot.app.RunApp;
import act.job.*;
import org.osgl.logging.L;
import org.osgl.logging.Logger;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.result.Result;
import static act.controller.Controller.Util.*;

/**
 * Demonstrate Job annotations
 */
public class JobApp {

    private static final Logger logger = L.get(JobApp.class);

    public JobApp() {
        //logger.info(new RuntimeException(), "JobApp initialized");
    }

    @GetAction("/")
    public static Result home(int limit, ActionContext context) {
        if (context.isAjax()) {
            return json(JobLog.logs(limit));
        }
        return render();
    }

    public static void main(String[] args) throws Exception {
        RunApp.start("Job Demo", Version.appVersion(), JobApp.class);
    }

    @OnAppStart(async = true)
    public void onAppStartAsync() {
        JobLog.log("onAppStartAsync called");
    }

    @OnAppStart
    public static void onAppStartSync() {
        JobLog.log("onAppStartSync called");
    }
 
}
