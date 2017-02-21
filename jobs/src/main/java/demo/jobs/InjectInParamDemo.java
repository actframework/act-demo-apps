package demo.jobs;

import act.job.Every;

import javax.inject.Inject;
import javax.inject.Named;

public class InjectInParamDemo {

    @Every("5s")
    public void schedule(@Named("bar") DataProcessService processor) {
        processor.process("DI in param");
    }
}
