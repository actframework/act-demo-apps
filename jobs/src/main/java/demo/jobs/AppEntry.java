package demo.jobs;

import act.Act;
import act.job.OnAppStart;
import act.ws.WsEndpoint;

/**
 * Demonstrate Job annotations
 */
@WsEndpoint("/ws")
public class AppEntry {

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
