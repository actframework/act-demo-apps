package act.fsa.appconfig;

import act.app.*;

/**
 * The simple hello world app.
 * <p>Run this app, try to update some of the code, then
 * press F5 in the browser to watch the immediate change
 * in the browser!</p>
 */
public class MyAppConfig extends AppConfigurator<MyAppConfig> {

    public MyAppConfig() {
        templateHome("/views");
    }

}
