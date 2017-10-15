package demo.jobs;

import act.controller.annotation.UrlContext;
import act.util.Async;
import act.util.ProgressGauge;
import org.osgl.mvc.annotation.PostAction;

import java.util.Random;

@UrlContext("/task")
public class Task {

    private int duration;

    @PostAction
    @Async
    public void create(ProgressGauge gauge) throws Exception {
        duration = randomDuration();
        gauge.updateMaxHint(duration);
        while (duration-- > 0) {
            gauge.step();
            Thread.sleep(50 * (new Random().nextInt(10) +  1));
        }
    }

    private int randomDuration() {
        return 5 + new Random().nextInt(200);
    }

}
