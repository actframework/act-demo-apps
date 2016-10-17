package demo.impl.bye;

import demo.ByeService;
import org.osgl.util.S;

public class ByeServiceImpl implements ByeService {
    @Override
    public String bye(String who) {
        return S.fmt("bye %s!", who);
    }
}
