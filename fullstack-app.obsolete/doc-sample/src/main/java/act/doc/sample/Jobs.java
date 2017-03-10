package act.doc.sample;

import act.job.AlongWith;
import act.job.Every;
import act.job.OnAppStart;

public class Jobs {
    @Every("50s")
    public void a() {
        System.out.println("job_a");
    }

    @AlongWith("act.doc.sample.Jobs.a")
    public void b() {
        System.out.println("job_b");
    }

    @OnAppStart
    public void c() {

    }

}
