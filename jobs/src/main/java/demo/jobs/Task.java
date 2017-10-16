package demo.jobs;

import act.controller.annotation.UrlContext;
import act.util.Async;
import act.util.ProgressGauge;
import org.osgl.mvc.annotation.PostAction;

import java.util.Random;

@UrlContext("/task")
public class Task {

    /**
     * This method simulate a long time task that should run in background.
     *
     * @param gauge
     *      framework injected progress gauge, developer can update task progress
     *      using this helper
     * @throws Exception
     */
    @Async
    @PostAction
    public void create(ProgressGauge gauge) throws Exception {
        int duration = randomDuration();
        gauge.updateMaxHint(duration);
        while (duration-- > 0) {
            gauge.step();
            Thread.sleep(10 * (new Random().nextInt(10) +  1));
        }
    }

    private int randomDuration() {
        return 5 + new Random().nextInt(200);
    }

}
