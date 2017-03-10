package demo.jobs;

import act.Act;
import act.app.ActionContext;
import act.job.OnAppStart;
import org.osgl.logging.L;
import org.osgl.logging.Logger;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.result.Result;

import static act.controller.Controller.Util.json;
import static act.controller.Controller.Util.render;

/**
 * Demonstrate Job annotations
 */
public class JobApp {

    private static final Logger logger = L.get(JobApp.class);

    public JobApp() {
        //logger.info(new RuntimeException(), "JobApp initialized");
    }

    @GetAction
    public Result home(int limit, ActionContext context) {
        if (context.isAjax()) {
            return json(JobLog.logs(limit));
        }
        return render();
    }

    @GetAction("foo")
    public void foo() {}

    public static void main(String[] args) throws Exception {
        Act.start("Job Demo");
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
