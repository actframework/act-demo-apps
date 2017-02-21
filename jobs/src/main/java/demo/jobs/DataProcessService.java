package demo.jobs;

import act.inject.AutoBind;

@AutoBind
public interface DataProcessService {
    void process(String context);
}
