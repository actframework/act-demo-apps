package demo.config;

import act.app.conf.AutoConfig;
import org.osgl.$;
import org.osgl.util.Const;

// AutoConfig automatically load configuration items with prefix specified
// The default prefix is "app.", thus @AutoConfig equals to @AutoConfig("app")
@AutoConfig
public class AppSettings {

    // Style one: CAPITALS separated by underscore
    public static final Const<Integer> FOO_BAR = $.constant(0);
    public static final Const<String> FOO_ZEE = $.constant("");
    public static final Const<String> FOO_AUTH_CODE = $.constant("xyz");

    // Style two: embedded class
    public static class db {
        public static Const<String> host = $.constant();
        public static Const<String> port = $.constant();
        public static Const<String> db = $.constant();
    }

    // Style two: embedded class
    public static class git {
        public static Const<String> protocol = $.constant();
        public static Const<String> repository = $.constant();
        public static Const<String> username = $.constant();
        public static Const<String> password = $.constant();
    }
}
