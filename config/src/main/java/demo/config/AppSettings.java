package demo.config;

import act.app.conf.AutoConfig;

@AutoConfig
public class AppSettings {

//    public static class foo {
//        public static int bar;
//        public static String zee;
//    }

    private static int FOO_BAR = 0;
    private static String FOO_ZEE = "";
    private static String FOO_AUTH_CODE = "xyz";

    public static int fooBar() {
        return FOO_BAR;
    }

    public static String fooZee() {
        return FOO_ZEE;
    }

    public static String fooAuthCode() {
        return FOO_AUTH_CODE;
    }

    public static class db {
        private static String host;
        public static String port;
        public static String db;
        public static String host() {
            return host;
        }
    }

    public static class git {
        public static String protocol;
        public static String repository;
        public static String username;
        public static String password;
    }
}
