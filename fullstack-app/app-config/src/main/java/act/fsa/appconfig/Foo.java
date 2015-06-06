package act.fsa.appconfig;

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
        return "See you soon!";
    }
}
