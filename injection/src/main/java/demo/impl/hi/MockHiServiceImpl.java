package demo.impl.hi;

import demo.HiService;

public class MockHiServiceImpl implements HiService {

    @Override
    public String sayHi(String who) {
        String action = "Mock hi";
        return action + " " + who;
    }

}
