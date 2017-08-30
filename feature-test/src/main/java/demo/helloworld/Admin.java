package demo.helloworld;

import act.cli.Command;
import act.cli.ReportProgress;
import act.cli.Required;
import act.controller.annotation.UrlContext;
import act.util.Async;
import act.util.ProgressGauge;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.util.S;

@UrlContext("admin")
@SuppressWarnings("unused")
public class Admin {

    @GetAction("random")
    public String random() {
        return S.random();
    }

    @Async
    @Command("countdown.async")
    public void testAsncCommand(
            @Required("specify the number") int num,
            ProgressGauge progressGauge
    ) throws Exception {
        progressGauge.updateMaxHint(num);
        for (int i = 0; i < num; ++i) {
            progressGauge.step();
            Thread.sleep(500);
        }
    }

    @Async
    @ReportProgress(type = ReportProgress.Type.TEXT)
    @Command("countdown")
    public void countDown(
            @Required("specify the number") int num,
            ProgressGauge progressGauge
    ) throws Exception {
        progressGauge.updateMaxHint(num);
        for (int i = 0; i < num; ++i) {
            progressGauge.step();
            Thread.sleep(500);
        }
    }

    @Command("gh322")
    public static void cliHandlerWithException() {
        throw new IllegalStateException("github issue 322");
    }

}
