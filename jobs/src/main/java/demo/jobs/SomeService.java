package demo.jobs;

import act.app.event.AppEventId;
import act.job.*;
import act.sys.Env;
import org.osgl.util.S;

/**
 * This class demonstrate how to use annotation to schedule jobs
 * in an ActFramework application
 */
public class SomeService {

    public String foo() {
        return S.random();
    }

    /**
     * This method will get called every x, where
     * `x` is configured through `every.check_status`
     * configuration
     */
    @Every(value = "every.check_status", id = "CHECK_STATUS")
    @Env.Group("job")
    public void checkStatus() {
        JobLog.log("SomeService.checkStatus");
    }

    @InvokeBefore("CHECK_STATUS")
    public void beforeCheckingStatus() {
        JobLog.log("Before checking status");
    }

    /**
     * This method will get started along with Application start up
     */
    @OnAppStart(id = "PREPARE-SOME_SERVICE")
    public void prepare() {
        JobLog.log("SomeService.prepare");
    }

    /**
     * This method will get invoked after {@link #prepare()} method is invoked.
     * <p>Note the method name {@code afterPrepare} doesn't matter, it could be any
     * other name.</p>
     */
    @InvokeAfter("PREPARE-SOME_SERVICE")
    public void afterPrepare() {
        JobLog.log("SomeService.afterPrepare");
    }

    /**
     * This method is scheduled to run every minute
     */
    @Cron("0 43 * * * ?")
    public void backup() {
        JobLog.log("SomeService.backup");
    }

    /**
     * This method is scheduled to run as per value of {@code cron.passwordReminder}
     * configuration. See {@code /resources/conf/app.properties}
     */
    @Cron("cron.passwordReminder")
    public void checkAndSendOutPasswordReminder() {
        JobLog.log("checking password expiration and sending out password reminder");
    }

    @OnAppEvent(AppEventId.EVENT_BUS_INITIALIZED)
    public static void onAppEventBusInitialized() {
        JobLog.log("onAppEventBusInitialized called");
    }

    @OnAppEvent(value = AppEventId.EVENT_BUS_INITIALIZED, async = true)
    public static void onAppEventBusInitializedAsync() {
        JobLog.log("onAppEventBusInitializedAsync called");
    }

}
