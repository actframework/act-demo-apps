# ActFramework Config Demo

This application demonstrates how to do configuration in ActFramework

## Start the application

Start the application in dev mode

```
mvn clean compile exec:exec
```

Start the application in prod mode

```
mvn clean package
cd target/dist
unzip *
./start
```

Once application has been started, you should see something like:
 
```
  ____                 __  _          ____                          
 / ___|  ___   _ __   / _|(_)  __ _  |  _ \   ___  _ __ ___    ___  
| |     / _ \ | '_ \ | |_ | | / _` | | | | | / _ \| '_ ` _ \  / _ \ 
| |___ | (_) || | | ||  _|| || (_| | | |_| ||  __/| | | | | || (_) |
 \____| \___/ |_| |_||_|  |_| \__, | |____/  \___||_| |_| |_| \___/ 
                              |___/                                 
                                powered by ActFramework v0.3.0-ec028

 version: 0.0.1-SNAPSHOT
base dir: /home/luog/p/act/samples/config/target/dist
 profile: prod
    mode: PROD

13:13:44.283 [main] INFO  a.Act - loading application(s) ...
13:13:44.290 [main] INFO  a.Act - App starting ....
13:13:44.368 [main] WARN  a.c.AppConfig - Application secret key not set! You are in the dangerous zone!!!
13:13:44.406 [main] WARN  a.a.DbServiceManager - DB service not initialized: No DB plugin found
13:13:44.560 [main] WARN  a.m.MailerConfig - smtp host configuration not found, will use mock smtp to send email
13:13:44.561 [main] WARN  a.c.AppConfig - host is not configured. Use localhost as hostname
13:13:44.683 [main] INFO  a.Act - App[Config Demo] loaded in 393ms
13:13:44.698 [main] INFO  o.xnio - XNIO version 3.3.6.Final
13:13:44.713 [main] INFO  o.x.nio - XNIO NIO Implementation Version 3.3.6.Final
13:13:44.852 [main] INFO  a.Act - network client hooked on port: 5460
13:13:44.852 [main] INFO  a.Act - CLI server started on port: 5461
13:13:44.853 [main] INFO  a.b.a.RunApp - it takes 1133ms to start the app
```

Then you can can open browser at `http://localhost:5460` to get the home page.

## Understanding the Demo app

The demo application contains an entry Java class `demo.config.ConfigDemo` with the main method: 

```java
public class ConfigDemo {

    ...
    
    public static void main(String[] args) throws Exception {
        RunApp.start("Config Demo", Version.appVersion(), ConfigDemo.class);
    }

}
```

The main method call `act.boot.app.RunApp.start(...)` method to bootstrap ActFramework and load this application:

* The `Version.appVersion()` is also provided by ActFramework, however it relies on the application to put a `app.version`
file under the `resources` folder.

* The `ConfigDemo.class` is the application entry class, which tells ActFramework it shall scan all classes under
`demo.config` package

### Action handler

The `ConfigDemo` class contains several action handler method:

```java
    // This is the root URL handler. It will load the template
    // stored in resources/views/demo/config/ConfigDemo.home.html
    // Note the template files are not sit under `rythm` because 
    // in the MyAppConfig class we configured the `templateHome`
    /// to be "views"
    @GetAction
    public void home() {
    }

    // This is going to be overwritten by routes.conf
    @GetAction("/bye")
    public void byePlayAndSpring() {
        text("bye Spring!!");
    }

    // This will display all application setttings
    @GetAction("/setting")
    public static Result showAppSettings() {
        return render();
    }
```

### Route table

There is a route table configuration file named `routes.conf` sit under `resources` folder:

```
GET /hi demo.config.Extra.hi
GET /bye demo.config.Extra.bye
```

When ActFramework starting the application and it spot the file as specified it will load the
content of the file into route table. 

In our example the first item in the route table overwrites the route mapping from `/hi` to 
`demo.config.ConfigDemo.byeSpring` method:
 
```
GET /hi demo.config.Extra.hi
```

It will also add an new route mapping item:

```
GET /bye demo.config.Extra.bye
```

### Configure with Java code

The `MyAppConfig` class extends `AppConfigurator` will get captured by ActFramework
on application bootup, and ActFramework will execute the `configure()` method of the instance:

```java
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
```

### Using configurations in application

The `AppSettings` class demonstrates how you can get configuration item injected into
your normal classes:

```java
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
```

Annotated with `AutoCopnfig` the fields in this class will be populated by ActFramework
automatically during application bootstrap process. Example of using the values can be found
in the `showAppSettings.html` rythm templates:

```html
@import demo.config.AppSettings
@extends(_base)

@section("style") {
<style>
  .setting-item {margin: 5px;}
  .label {background-color: #efece6; padding: 1px 8px;min-width: 100px; display: inline-block;text-align: right}
  .val {border-bottom: 1px solid #efece6;min-width: 200px;display: inline-block}
</style>
}

@def item(String label, Object val) {
<div class="setting-item">
  <span class="label">@label</span>
  <span class="val">@val</span>
</div>
}

<h1>App Settings</h1>
<h4>Foo</h4>
@item("foo.bar", AppSettings.FOO_BAR)
@item("foo.zee", AppSettings.FOO_ZEE)
@item("foo.auth_code", AppSettings.FOO_AUTH_CODE)

<h4>DB</h4>
@item("db.host", AppSettings.db.host)
@item("db.port", AppSettings.db.port)
@item("db.db", AppSettings.db.db)

<h4>git</h4>
@item("git.protocol", AppSettings.git.protocol)
@item("git.repository", AppSettings.git.repository)
@item("git.username", AppSettings.git.username)
@item("git.password", AppSettings.git.password)
```

And of course you can also use the values in other classes
 
## FAQ

**Question**: Why do I get the following error message ?

`Cannot encrypt/decrypt! Please download Java Crypto Extension pack from Oracle`

**Answer**: Please download and install the JCE pack from Oracle for
[Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html) or 
[Java 7](http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html)
