package demo.injection.impl.hi;

import demo.injection.HiService;
import org.osgl.util.S;

public class MockHiServiceImpl implements HiService {

    @Override
    public String sayHi(String who) {
        return S.fmt("Hi %s (in DEV mode)!", who);
    }

}
