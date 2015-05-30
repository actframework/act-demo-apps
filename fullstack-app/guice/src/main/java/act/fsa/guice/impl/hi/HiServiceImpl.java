package act.fsa.guice.impl.hi;

import act.fsa.guice.HiService;

public class HiServiceImpl implements HiService {
    @Override
    public String sayHi(String who) {
        return "Hi " + who;
    }
}
