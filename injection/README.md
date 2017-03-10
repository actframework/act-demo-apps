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

After app started you should see something in the console looks like:

``` ___              _   _  ___     _    _         _  
  |   |\ |    |  |_  /    |     | \  |_  |\/|  / \ 
 _|_  | \|  \_|  |_  \_   |     |_/  |_  |  |  \_/ 
                                                   
                powered by ActFramework R1.0.0-8707

 version: 1.0.0
scan pkg: demo.injection
base dir: /home/luog/p/act/samples/injection/target/dist
     pid: 29954
 profile: prod
    mode: PROD

     zen: Explicit is better than implicit.

15:40:23.391 [main] INFO  a.Act - loading application(s) ...
15:40:23.395 [main] INFO  a.Act - App starting ....
15:40:23.472 [main] WARN  a.c.AppConfig - Application secret key not set! You are in the dangerous zone!!!
15:40:23.503 [main] WARN  a.a.DbServiceManager - DB service not initialized: No DB plugin found
15:40:23.732 [main] WARN  a.m.MailerConfig - smtp host configuration not found, will use mock smtp to send email
15:40:23.732 [main] WARN  a.c.AppConfig - host is not configured. Use localhost as hostname
15:40:23.850 [main] INFO  a.Act - App[Inject Demo] loaded in 455ms
15:40:23.867 [main] INFO  o.xnio - XNIO version 3.3.6.Final
15:40:23.882 [main] INFO  o.x.nio - XNIO NIO Implementation Version 3.3.6.Final
15:40:23.973 [main] INFO  a.Act - network client hooked on port: 5460
15:40:23.974 [main] INFO  a.Act - CLI server started on port: 5461
15:40:23.974 [main] INFO  a.Act - it takes 1472ms to start the app
```

Once application has been started, you can open browser and locate to `http://localhost:5460` get the home page.


## Understanding the Demo app

The demo application contains a main application entry plus a set of service interfaces, implementation, as well 
as modules that specify the service bindings

### Demo app entry

The app entry class `demo.injection.InjectionApp` defines the main method that will start the app within 
ActFramework:

```java
    public static void main(String[] args) throws Exception {
        Act.start("Inject Demo");
    }
```

The main method call `act.Act.start(...)` method to bootstrap ActFramework and load this application. The parameter "Injection Demo" is the application name


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
/**
 * Overwrite the auto binding on {@link GreetingService} so we always
 * use {@link GreetingServiceImpl} in regarding to the current app
 * run environment mode.
 */
public class GreetingModule extends Module {
    @Override
    protected void configure() {
        // Comment out the following line and refresh your browser page
        // at http://localhost:5460/greeting to observe the changes
        bind(GreetingService.class).to(GreetingServiceImpl.class);
    }
}
```

As shown above the `GreetingModule` bind the `GreetingServiceImpl` to `GreetingService` using provided factory
method. 

**Tips** Name your module class to `XyzModule` so ActFramework will automatically register the class as dependency 
injection module. Otherwise you need to tag the class with `@act.inject.ModuleTag` annotation

**Note** The `GreetingService` interface is declared with `@AutoBind`, that tells ActFramework to automatically look for the implementation on application start:

```java
@AutoBind
public interface GreetingService {
    String greeting();
}
```

So one doesn't need to specify the `GreentingService` binding. It will automatically look up the implementations that matches the current environment. Specifically when
the app is running in `DEV` mode, it will bind to `GreetingService` to `MockGreetingServiceImpl` which is annotated with `@Env.Mode(Act.Mode.DEV)`. Otherwise it will
bind the `GreetingService` to `GreetingServiceImpl` which is annotated with `@Env.Mode(Act.Mode.PROD)`.

### Hi and Bye modules

We created two modules to demonstrate how to use module to inject `HiServcie` and `ByeService` in different running mode:

Module for PROD mode

```java
/**
 * This module demonstrate how to do binding by
 * extending {@link Module} and calling bind API
 * in the {@link Module#configure()} method
 *
 * @see DevModeHiByeModule
 */
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
/**
 * This module demonstrate how to create module by providing
 * factory methods
 *
 * @see HiByeModule
 */
@ModuleTag
@Env.Mode(DEV)
public class DevModeHiByeModule {

    @Provides
    public static HiService foo(MockHiServiceImpl hiService) {
        return hiService;
    }

    @Provides
    public ByeService bar() {
        return Act.newInstance(MockByeServiceImpl.class);
    }
}
```


**Tips** ActFramework will automatically register module classes in the following three cases

1. public non-abstract classes that extends `org.osgl.inject.Module`
2. public classes that annotated with `act.inject.ModuleTag`
3. public classes whose name ends with `Module`

## FAQ

**Question**: Why do I get the following error message ?

`Cannot encrypt/decrypt! Please download Java Crypto Extension pack from Oracle`

**Answer**: Please download and install the JCE pack from Oracle for
[Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html) or 
[Java 7](http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html)

**Question**: Why do I get `401 Unauthorized` when posting to `/hi` using PostMan?

**Answer**: ActFramework by default enable the 
[CSRF](https://www.owasp.org/index.php/Cross-Site_Request_Forgery_(CSRF)_Prevention_Cheat_Sheet) protection, 
when you post directly from post man you don't have the `__csrf__` parameter, thus the request is rejected. 

You can run the app with `-Dcsrf=false` to turn off CSRF protection
