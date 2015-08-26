package act.fsa.appconfig;

import act.app.conf.AutoConfig;

@AutoConfig
public class AppSettings {

//    public static class foo {
//        public static int bar;
//        public static String zee;
//    }

    public final static int FOO_BAR = 0;
    public final static String FOO_ZEE = "";
    public final static String FOO_AUTH_CODE = "xyz";

    public static class db {
        public static String host;
        public static String port;
        public static String db;
    }

    public static class git {
        public static String protocol;
        public static String repository;
        public static String username;
        public static String password;
    }
}
