package demo.helloworld;

import act.cli.Command;

/**
 * Github issue #367.
 *
 * It shall not try to instantiate commander class for command implemented on static method
 */
public enum GH367 {
    ;

    @Command("gh.367")
    public static void test() {
    }
}
