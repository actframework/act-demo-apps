package demo.progress;

import act.Act;
import act.cli.Command;
import act.cli.ReportProgress;
import act.cli.Required;
import act.util.Async;
import act.util.ProgressGauge;
import org.osgl.mvc.annotation.GetAction;

@SuppressWarnings("unused")
public class Application {

    @GetAction
    public void home() {
    }

    @Async
    @Command(name = "countdown", help = "countdown the number in background")
    public void countdown(
            @Required("specify the number") int number,
            ProgressGauge progressGauge
    ) throws Exception {
        progressGauge.updateMaxHint(number);
        for (int i = 0; i < number; ++i) {
            progressGauge.step();
            Thread.sleep(500);
        }
    }

    @Async
    @ReportProgress
    @Command(name = "countdown.track", help = "countdown the number and track")
    public void countdownAndReportProgress(
            @Required("specify the number") int number,
            ProgressGauge progressGauge
    ) throws Exception {
        progressGauge.updateMaxHint(number);
        for (int i = 0; i < number; ++i) {
            progressGauge.step();
            Thread.sleep(200);
        }
    }

    public static void main(String[] args) throws Exception {
        Act.start();
    }
}
