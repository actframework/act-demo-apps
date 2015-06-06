package act.fsa.appconfig;

import act.app.*;
import org.osgl.http.H;

/**
 * Application could be configured using code if configuration file
 * is not your favorite. Just create a public class that extends
 * {@link AppConfigurator} and override the {@link AppConfigurator#configure()}
 * method. ACT framework will sense your code base configuration and load it
 * during bootstrap process
 */
public class MyAppConfig extends AppConfigurator<MyAppConfig> {

    // So override the configure() method
    // to do our code based configurations
    @Override
    public void configure() {
        configureTemplateHome();
        configureRoutes();
    }

    private void configureTemplateHome() {
        // this set the template home path to "views"
        // by default it is "default", which in the end
        // mapped to "rythm" because default view engine is rythm
        templateHome("views");
    }

    // Note the route configurations setup here will override
    // route configurations done by controller action method annotation
    // however routes configured in routes table file will take the precedence over
    // this configuration
    private void configureRoutes() {
        // this map path "/foo" to AppConfigDemoApp.sayHello method
        // the mapping is on HTTP GET method only
        route("/foo").on(GET).to(AppConfigDemoApp.class, "sayHello");

        // this map the "/foo" context to Foo.class and automatically
        // map sub path to all public method of Foo.class
        // the mapping is on all supported HTTP methods
        route("/foo").to(Foo.class);

        // this will remap "/hello" from AppConfigDemoApp.sayHello to AppConfigDemoApp.greeting
        // the mapping is on all supported HTTP methods
        route("/hello").to(AppConfigDemoApp.class, "greeting");
    }

}
