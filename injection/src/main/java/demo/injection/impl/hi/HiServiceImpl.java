package demo.injection.impl.hi;

import demo.injection.HiService;

public class HiServiceImpl implements HiService {
    @Override
    public String sayHi(String who) {
        String action = "hi";
        return action + " " + who;
    }
}
