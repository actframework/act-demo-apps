package demo.config;

import act.app.conf.AutoConfig;
import org.osgl.$;
import org.osgl.util.Const;
import org.osgl.util.S;

/**
 * A demo controller which is mapped by routes.conf file
 */
@AutoConfig("x")
public class Extra {

    private static final Const<String> TO = $.constant();

    public String hi(String who) {
        return S.blank(who) ? "Hi, how are you going?" : S.fmt("Hi %s, how are you going?", who);
    }

    public String bye() {
        return S.fmt("%s, see you soon!", TO.get());
    }

}
