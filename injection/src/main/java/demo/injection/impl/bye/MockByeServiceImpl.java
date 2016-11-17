package demo.injection.impl.bye;

import demo.injection.ByeService;
import org.osgl.http.H;
import org.osgl.util.S;

import javax.inject.Inject;

public class MockByeServiceImpl implements ByeService {

    @Inject
    private H.Request request;

    public String bye(String who) {
        return S.fmt("In answering request sent from [%s], we say bye %s! (in DEV mode)", request.ip(), who);
    }

}
