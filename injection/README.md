# ActFramework Injection Demo

This application demonstrates how to use dependency injection in your actframework application

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
./start -Dhttp.secure=false
```

**Note** You need to turn off `http.secure` when running locally otherwise POST request will be blocked due to CSRF 
check failure

Once application has been started, you can open browser and locate to `http://localhost:5460` get the home page.


## Understanding the Demo app

The demo application contains a main application entry plus a set of service interfaces, implementation, as well 
as modules that specify the service bindings

### Demo app entry

The app entry class `demo.injection.InjectionApp` defines the main method that will start the app within 
ActFramework:

```java
    public static void main(String[] args) throws Exception {
        RunApp.start("Inject Demo", Version.appVersion(), InjectionApp.class);
    }
```

Here `RunApp` is ActFramework class that bootstrap the application.

The `Version.appVersion()` is also provided by ActFramework, however it relies on the application to put a `app.version`
file under the `resources` folder.

The `InjectionApp.class` is the application entry class, which tells ActFramework it shall scan all classes under
`demo.injection` package

The class `InjectionApp` has a constructor which will inject `HiService` and `ByeService` implementation:

```java
    @Inject
    public InjectionApp(HiService hi, ByeService bye) {
        this.hi = hi;
        this.bye = bye;
    }
```

There are a set of controller action handlers defined in `InjectionApp` class:

#### Home handler
```java
    @GetAction
    public void home() {
    }
```
Home handler answers `GET` request sent to `/` and load template `rythm/demo/injection/InjectionApp/home.html` to 
render the response

#### Greeting handler

```java
    @GetAction("/greeting")
    public String greeting(GreetingService greeting) {
        return greeting.greeting();
    }
```
Greeting handler answers `GET` request sent to `/greeting`. The `GreetingService` will be injected by ActFramework when 
calling this handler. It then relies on implementation of `GreetingService` to generate response

#### Hi and Bye handlers

```java
    @PostAction("/hi")
    public Result hi(String who) {
        String message = hi.sayHi(who);
        return render(message);
    }

    @PostAction("/bye")
    public void bye(String who) {
        text(bye.bye(who));
    }
```

The `hi` and `bye` handlers answers `POST` requests sent to `/hi` and `/bye` respectively. They all accept one post
parameter named `who`.

`hi` handler will render the response by merging the message returned by `HiService` implementation with template
 ``rythm/demo/injection/InjectionApp/hi.html`
 
`bye` handler respond back directly with the message returned by `ByeService` implementation with `text/plain` MIME type

### Services interfaces, implemetations and modules

The there are three services defined in the demo application:

* `GreetingServcie`
* `ByeService`
* `HiService`

The implementations of these services are defined in classes in `impl` package. Note we have defined one
implementation for `GreetingService` while there are two implementations defined for `HiService` and `ByeService`
respectively. The `MockXxx` implementations are supposed to be used when app is running in `DEV` mode while the
 other one is supposed to be running in `PROD` mode.
 
The magic of binding happens in the module classes found in `modules` package.

### Greeting module

```java
public class GreetingModule  {
    @Provides
    public GreetingService greetingService(GreetingServiceImpl greetingService) {
        return greetingService;
    }
}
```

As shown above the `GreetingModule` bind the `GreetingServiceImpl` to `GreetingService` using provided factory
method. 

**Tips** Name your module class to `XyzModule` so ActFramework will automatically register the class as dependency 
injection module. Otherwise you need to tag the class with `@act.inject.ModuleTag` annotation

### Hi and Bye modules

Module for PROD mode

```java
@Env.Mode(PROD)
public class HiByeModule extends Module {
    @Override
    protected void configure() {
        bind(HiService.class).to(HiServiceImpl.class);
        bind(ByeService.class).to(ByeServiceImpl.class);
    }
}
```

Module for DEV mode:

```java
@Env.Mode(DEV)
public class DevModeHiByeModule extends Module {
    @Override
    protected void configure() {
        bind(HiService.class).to(MockHiServiceImpl.class);
        bind(ByeService.class).to(MockByeServiceImpl.class);
    }
}
```

To demonstrate a different way of defining module, we extends the `HiByeModule` from `org.osgl.inject.Module` and
implement the `configure()` method, in which we have the `bind` statement to define the bindings.

**Tips** ActFramework will automatically register any class that extends from 
`org.osgl.inject.Module` class.

The tricky part is the `Env.Mode` annotation which specifies the environment in which the module shall be loaded.
If the `Env.Mode` annotation is not presented, then the module shall be loaded in any environment. Otherwise the
module will be loaded only when the running environment corresponding to the `Env.Mode` specification.

## FAQ

**Question**: Why do I get the following error message ?

`Cannot encrypt/decrypt! Please download Java Crypto Extension pack from Oracle`

**Answer**: Please download the JCE pack from Oracle for
[Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html) or 
[Java 7](http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html)

**Question**: Why do I get `401 Unauthorized` when posting to `/hi` using PostMan?

**Answer**: ActFramework by default enable the 
[CSRF](https://www.owasp.org/index.php/Cross-Site_Request_Forgery_(CSRF)_Prevention_Cheat_Sheet) protection, 
when you post directly from post man you don't have the `__csrf__` parameter, thus the request is rejected. 

You can run the app with `-Dcsrf=false` to turn off CSRF protection
