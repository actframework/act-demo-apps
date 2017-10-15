package demo.jobs;

import act.Act;
import act.job.OnAppStart;
import org.osgl.mvc.annotation.WsAction;

/**
 * Demonstrate Job annotations
 */
public class JobApp {

    @WsAction("/ws")
    public static void placeholder() {
    }

    @OnAppStart(async = true)
    public void onAppStartAsync() {
        JobLog.log("onAppStartAsync called");
    }

    @OnAppStart
    public static void onAppStartSync() {
        JobLog.log("onAppStartSync called");
    }

    public static void main(String[] args) throws Exception {
        Act.start("Job Demo");
    }

}
