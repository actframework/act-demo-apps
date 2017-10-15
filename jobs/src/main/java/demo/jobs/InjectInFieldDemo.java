package demo.jobs;

import act.job.Every;

import javax.inject.Inject;
import javax.inject.Named;

public class InjectInFieldDemo {
    @Inject
    @Named("foo")
    private DataProcessService processor;

    @Every("30s")
    public void schedule() {
        processor.process("DI in field");
    }
}
