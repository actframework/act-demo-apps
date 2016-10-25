# ActFramework HelloWorld Demo

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

Once application has been started, you can open browser and locate to `http://localhost:5460` get the home page.


## About Act Excel plugin

Excel reporting feature is not provded directly to ActFramework. You have to add the following dependency into your
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

The demo application contains a single Java class `demo.excel.ExcelApp`

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

The html template `home.html` is located at `resources/rythm/demo/excel/ExcelApp`. Literally the path is corresponding
to the package/class/method hierarchy, based in `resources/rythm` template root. Note the render argument is 
a list of `Employee` indexed by name `result` because it is returned directly from the action handler method.

The template is created using [Rythm Engine](http://rythmengine.org). For more information about Rythm engine, please
 visit the official site at http://rythmenigne.org

When request accept header value is "text/csv", because we don't have a template for `csv` request, ActFramework will
 generate CSV based on the `Employee` class's fields.
 
When request accept header value is `application/vnd.ms-excel`, ActFramework will look for the excel template at

`resources/excel/demo/excel/ExcelApp/home.xls`

When request accept header value is `application/vnd.openxmlformats-officedocument.spreadsheetml.sheet`, ActFramework
will look for the template at

`resources/excel/demo/excel/ExcelApp/home.xlsx`

### Turn on `content_suffix.aware` configuration

When the `content_suffix.aware` configuration is turned on, ActFramework will try to update the request `Accept` header
 based on URL. In this example:
 
 | URL | Accept | Effective URL |
 | --- | ------ | ------------- |
 | /csv | text/csv | / |
 | /json | application/json | / |
 | /xls | application/vnd.ms-excel | / |
 | /xlsx | application/vnd.openxmlformats-officedocument.spreadsheetml.sheet | / |
 
The configuration in this application could be found in `resources/app.conf` file. The use of content_suffix aware
URL could be found in the `resources/rythm/demo/excel/ExcelApp/home.html` template
 
## FAQ

**Question**: Why do I get the following error message ?

`Cannot encrypt/decrypt! Please download Java Crypto Extension pack from Oracle`

**Answer**: Please download and install the JCE pack from Oracle for
[Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html) or 
[Java 7](http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html)
