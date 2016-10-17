package demo.impl.hi;

import demo.HiService;

public class HiServiceImpl implements HiService {
    @Override
    public String sayHi(String who) {
        String action = "hi";
        return action + " " + who;
    }
}
