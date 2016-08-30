package act.fsa.helloworld;

import act.controller.Controller;
import act.inject.HeaderVariable;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.result.Result;
import org.osgl.web.util.UserAgent;

import java.util.List;

public class HeaderAndUserAgent extends Controller.Util {

    @GetAction("acceptEncoding")
    public String[] getAcceptEncoding(@HeaderVariable String[] acceptEncoding) {
        return acceptEncoding;
    }

    @GetAction("acceptLanguage")
    public List<String> acceptLanguage(@HeaderVariable List<String> acceptLanguage) {
        return acceptLanguage;
    }

    @GetAction({"uir", "upgrade-insecure-requests"})
    public int uir(@HeaderVariable int upgradeInsecureRequests) {
        return upgradeInsecureRequests;
    }

    @GetAction("header")
    public Result headerDemo(
            @HeaderVariable List<String> accept,
            @HeaderVariable String[] acceptEncoding,
            @HeaderVariable int upgradeInsecureRequests
    ) {
        return jsonMap(accept, acceptEncoding, upgradeInsecureRequests);
    }

    @GetAction("ua/mobile")
    public boolean isMobile(UserAgent userAgent) {
        return userAgent.isMobile();
    }

    @GetAction("ua/ie")
    public boolean isIE(UserAgent userAgent) {
        return userAgent.isIE();
    }

    @GetAction("/ua")
    public Result userAgent(UserAgent userAgent) {
        boolean ie = userAgent.isIE();
        boolean mobile = userAgent.isMobile();
        boolean chrome = userAgent.isChrome();
        boolean firefox = userAgent.isFirefox();
        boolean webkit = userAgent.isWebKit();
        boolean safari = userAgent.isSafari();
        return jsonMap(ie, mobile, chrome, firefox, webkit, safari);
    }
}
