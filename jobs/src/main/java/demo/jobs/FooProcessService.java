package demo.jobs;

import javax.inject.Named;

@Named("foo")
public class FooProcessService implements DataProcessService {
    @Override
    public void process(String context) {
        JobLog.log("FooProcessService: processing data " + context);
    }
}
