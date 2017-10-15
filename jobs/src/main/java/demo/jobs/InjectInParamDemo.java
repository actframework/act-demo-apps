package demo.jobs;

import act.job.Every;

import javax.inject.Named;

public class InjectInParamDemo {

    @Every("50s")
    public void schedule(@Named("bar") DataProcessService processor) {
        processor.process("DI in param");
    }
}
