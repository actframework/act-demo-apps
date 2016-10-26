package demo.config;

import act.app.conf.AppConfigurator;

/**
 * Application could be configured using code if configuration file
 * is not your favorite. Just create a public class that extends
 * {@link act.app.conf.AppConfigurator} and override the {@link act.app.conf.AppConfigurator#configure()}
 * method. ACT framework will sense your code base configuration and load it
 * during bootstrap process
 */
public class MyAppConfig extends AppConfigurator<MyAppConfig> {

    // So override the configure() method
    // to do our code based configurations
    @Override
    public void configure() {
        configureTemplateHome();
        configureAppProps();
    }

    private void configureTemplateHome() {
        // this set the template home path to "views"
        // by default it is "default", which in the end
        // mapped to "rythm" because default view engine is rythm
        templateHome("views");
    }

    private void configureAppProps() {
        prop("x.to", "X-man");
    }

}
