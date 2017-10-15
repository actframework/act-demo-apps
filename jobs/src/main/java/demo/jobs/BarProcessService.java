package demo.jobs;

import javax.inject.Named;

@Named("bar")
public class BarProcessService implements DataProcessService {
    @Override
    public void process(String context) {
        JobLog.log("BarProcessService: processing data | " + context);
    }
}
