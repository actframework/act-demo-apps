package act.fsa.todo;

import act.job.Cron;
import act.job.Every;
import act.job.InvokeAfter;
import act.job.OnAppStart;
import org.osgl.util.S;

public class SomeService {

    public String foo() {
        return S.random();
    }

    @Every("5s")
    public void checkStatus() {
        JobLog.log("SomeService.checkStatus");
    }

    @OnAppStart
    public void prepare() {
        JobLog.log("SomeService.prepare");
    }

    @InvokeAfter("act.fsa.todo.SomeService.prepare")
    public void afterPrepare() {
        JobLog.log("SomeService.afterPrepare");
    }

    @Cron("0 * * * * ?")
    public void backup() {
        JobLog.log("SomeService.backup");
    }
}
