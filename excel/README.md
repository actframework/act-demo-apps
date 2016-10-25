# ActFramework Excel Demo

This application demonstrates how to use generate Excel report in ActFramework application

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
| ____|__  __  ___   ___ | | |  _ \   ___  _ __ ___    ___  
|  _|  \ \/ / / __| / _ \| | | | | | / _ \| '_ ` _ \  / _ \ 
| |___  >  < | (__ |  __/| | | |_| ||  __/| | | | | || (_) |
|_____|/_/\_\ \___| \___||_| |____/  \___||_| |_| |_| \___/ 
                                                            
                        powered by ActFramework v0.3.0-ec028

 version: 0.0.1-SNAPSHOT
base dir: /home/luog/p/act/samples/excel/target/dist
 profile: common
    mode: PROD

14:01:20.879 [main] INFO  a.Act - loading application(s) ...
14:01:20.883 [main] INFO  a.Act - App starting ....
14:01:20.939 [main] WARN  a.c.AppConfig - Application secret key not set! You are in the dangerous zone!!!
14:01:20.963 [main] WARN  a.a.DbServiceManager - DB service not initialized: No DB plugin found
14:01:21.159 [main] WARN  a.m.MailerConfig - smtp host configuration not found, will use mock smtp to send email
14:01:21.159 [main] WARN  a.c.AppConfig - host is not configured. Use localhost as hostname
14:01:21.277 [main] INFO  a.Act - App[Excel Demo] loaded in 394ms
14:01:21.292 [main] INFO  o.xnio - XNIO version 3.3.6.Final
14:01:21.308 [main] INFO  o.x.nio - XNIO NIO Implementation Version 3.3.6.Final
14:01:21.410 [main] INFO  a.Act - network client hooked on port: 5460
14:01:21.411 [main] INFO  a.Act - CLI server started on port: 5461
14:01:21.412 [main] INFO  a.b.a.RunApp - it takes 2906ms to start the app
```

Now you can open browser at `http://localhost:5460` to view the home page.

**Tips** You can also run the app in your IDE by run the main entry class `demo.excel.ExcelApp`

## About Act Excel plugin

Excel reporting feature is not provided directly in ActFramework. You need to add the following dependency into your
`pom.xml` file:

```xml
    <dependency>
      <groupId>org.actframework</groupId>
      <artifactId>act-excel</artifactId>
      <version>${act-excel.version}</version>
    </dependency>
```

At the moment act-excel version is `0.0.1-SNAPSHOT`. 

The plugin implemented `act.view.View` using [jxls](http://jxls.sourceforge.net/) library

## Understanding the Demo app

The demo application has a main entry class: `demo.excel.ExcelApp`

```java
public class ExcelApp {

    @LoadCollection(TestDataGenerator.class)
    private List<Employee> employees;

    @GetAction
    public List<Employee> home(ActionContext context) {
        return employees;
    }

    public static void main(String[] args) throws Exception {
        RunApp.start("Excel Demo", Version.appVersion(), ExcelApp.class);
    }

}
```

The main method call `act.boot.app.RunApp.start(...)` method to bootstrap ActFramework and load this application:

* The `Version.appVersion()` is also provided by ActFramework, however it relies on the application to put a 
`app.version` file under the `resources` folder.

* The `ExcelApp.class` is the application entry class, which tells ActFramework it shall scan all classes under
`demo.excel` package

### Action handler

The `ExcelApp` class contains one action handler method:

```java
    @GetAction
    public List<Employee> home(ActionContext context) {
        return employees;
    }
```

`@GetAction` annotation without parameter tells ActFramework that this method is a handler that answers request sent to
`/`. 

This simple action handler returns a list of `Employee` data. Which will be further processed by template engines to 
render response based on request Accept header value. By default it will render the `html` template.

### Content negotiation and template engine dispatch

Based on request's `Accept` header, ActFramework choose different template engine to render the response

| content type | template engine | location |
| -----------  | --------------- | -------- |
| text/html (default) | rythm | resources/rythm/demo/excel/ExcelApp/home.html |
| text/csv | rythm | resources/rythm/demo/excel/ExcelApp/home.csv |
| application/vnd.ms-excel | excel | resources/excel/demo/excel/ExcelApp/home.xls |
| application/vnd.openxmlformats-officedocument.spreadsheetml.sheet | excel | resources/excel/demo/excel/ExcelApp/home.xlsx |

In our application there is no template resource `resources/rythm/demo/excel/ExcelApp/home.csv`, thus when the request
is looking for a csv download, ActFramework will call built-in CSV file generate to prepare the file based on the Java
 reflection to get the `Employee` 's properties.

### About `content_suffix.aware` configuration

When the `content_suffix.aware` configuration is turned on, ActFramework will try to update the request `Accept` header
 based on URL. In this example:
 

| URL | Accept header | Effective URL |
| -----------  | --------------- | -------- |
| `/csv` | `text/csv` | `/` |
| `/json`   |  application/json | `/` |
| `/json` | `application/json` | `/` |
| `/xlsx` | `application/vnd.openxmlformats-officedocument.spreadsheetml.sheet` | `/` |


The configuration in this application could be found in `resources/app.conf` file. The use of content_suffix aware
URL could be found in the `resources/rythm/demo/excel/ExcelApp/home.html` template
 
## FAQ

**Question**: Why do I get the following error message ?

`Cannot encrypt/decrypt! Please download Java Crypto Extension pack from Oracle`

**Answer**: Please download and install the JCE pack from Oracle for
[Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html) or 
[Java 7](http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html)
