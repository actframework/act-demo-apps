package demo.helloworld;

import act.cli.Command;
import act.cli.ReportProgress;
import act.cli.Required;
import act.util.Async;
import act.util.ProgressGauge;

public class GH266 {
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
}
