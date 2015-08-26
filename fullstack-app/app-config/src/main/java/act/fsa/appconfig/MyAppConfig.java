package act.fsa.appconfig;

/**
 * Application could be configured using code if configuration file
 * is not your favorite. Just create a public class that extends
 * {@link act.app.conf.AppConfigurator} and override the {@link act.app.conf.AppConfigurator#configure()}
 * method. ACT framework will sense your code base configuration and load it
 * during bootstrap process
 */
public class MyAppConfig extends act.app.conf.AppConfigurator<MyAppConfig> {

    // So override the configure() method
    // to do our code based configurations
    @Override
    public void configure() {
        configureTemplateHome();
        configureRoutes();
        configureAppProps();
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

    private void configureAppProps() {
        prop("app.foo.bar", 5);
        prop("app.foo.zee", "foo");
        prop("app.foo.auth_code", "apple");
        prop("app.db.host", "127.0.0.1");
        prop("app.db.port", "1234");
        prop("app.db.db", "catfish");
        prop("app.git.protocol", "git");
        prop("app.git.repository", "github.com/actframework/actframework");
        prop("app.git.username", "tomcat");
        prop("app.git.password", "$%FVSFAFdhdd07sf#!<()");
        prop("foo.to", "X-man");
    }

}
