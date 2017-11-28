package demo.config;

import act.app.conf.AutoConfig;
import act.inject.DefaultValue;
import org.osgl.$;
import org.osgl.util.Const;
import org.osgl.util.S;
import org.osgl.inject.annotation.Configuration;

/**
 * A demo controller which is mapped by routes.conf file
 */
@AutoConfig("x")
public class Extra {

    private static final Const<String> TO = $.constant();

    @Configuration("x.to")
    private static String to;

    public String hi(@DefaultValue("body") String who) {
        return S.fmt("Hi %s, how are you going?", who);
    }

    public String bye() {
        return S.fmt("%s, see you soon!", TO.get());
    }

}
