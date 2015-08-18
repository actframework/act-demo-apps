package act.fsa.jobs;

import act.job.Cron;
import act.job.Every;
import act.job.InvokeAfter;
import act.job.OnAppStart;
import org.osgl.util.S;

/**
 * This class demonstrate who to use annotation to schedule jobs
 * running with ActFramework
 */
public class SomeService {

    public String foo() {
        return S.random();
    }

    /**
     * This method will get called every 5 seconds
     */
    @Every("5s")
    public void checkStatus() {
        JobLog.log("SomeService.checkStatus");
    }

    /**
     * This method will get started along with Application start up
     */
    @OnAppStart
    public void prepare() {
        JobLog.log("SomeService.prepare");
    }

    /**
     * This method will get invoked after {@link #prepare()} method is invoked.
     * <p>Note the method name {@code afterPrepare} doesn't matter, it could be any
     * other name.</p>
     */
    @InvokeAfter("act.fsa.jobs.SomeService.prepare")
    public void afterPrepare() {
        JobLog.log("SomeService.afterPrepare");
    }

    /**
     * This method is scheduled to run every minute
     */
    @Cron("0 * * * * ?")
    public void backup() {
        JobLog.log("SomeService.backup");
    }

    /**
     * This method is scheduled to run as per value of {@code cron.passwordReminder}
     * configuration. See {@code /resources/conf/cron.properties}
     */
    @Cron("cron.passwordReminder")
    public void checkAndSendOutPasswordReminder() {
        JobLog.log("checking password expiration and sending out password reminder");
    }
}
