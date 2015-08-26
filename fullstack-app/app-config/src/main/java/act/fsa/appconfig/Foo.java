package act.fsa.appconfig;

import act.app.conf.AutoConfig;
import org.osgl.util.S;

/**
 * A demo controller with all methods automatically mapped
 * to route by {@link MyAppConfig}
 */
public class Foo {
    public String hi(String who) {
        return S.blank(who) ? "Hi, how are you going?" : S.fmt("Hi %s, how are you going?", who);
    }

    public String bye() {
        return S.fmt("%s, see you soon!", Container.FooConfig.to);
    }

    public static class Container {
        @AutoConfig("foo")
        public static class FooConfig {
            public static String to = "abc";
        }
    }
}
