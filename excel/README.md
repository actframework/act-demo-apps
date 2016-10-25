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

## Performance

Data scale:

* Number of data (Employee): 50
* XLS template size: 22KB
* XLSX template size: 11KB

Hardware (my local laptop):

* KINGSTON SUV400S37240G
* 16G RAM
* i7-4700MQ

| Request Type | Request # | Concurrency level | Time/Req (ms) | Time/Req (ms, across all concurrent requests) |
| --- | --- | --- | --- | --- |
| HTML | 10k | 20 | 7.05 | 0.35 |
| JSON | 10k | 20 | 3.45 | 0.17 |
| CSV | 10k | 20 | 5.38 | 0.27 |
| XLS | 1k | 5 | 11.09 | 2.22 |
| XLSX | 1k | 5 | 63.24 | 12.65 |


ab log for getting HTML data:

```
ab -n 10000 -c 20 http://localhost:5460/
This is ApacheBench, Version 2.3 <$Revision: 1706008 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 1000 requests
Completed 2000 requests
Completed 3000 requests
Completed 4000 requests
Completed 5000 requests
Completed 6000 requests
Completed 7000 requests
Completed 8000 requests
Completed 9000 requests
Completed 10000 requests
Finished 10000 requests


Server Software:        
Server Hostname:        localhost
Server Port:            5460

Document Path:          /
Document Length:        4766 bytes

Concurrency Level:      20
Time taken for tests:   3.525 seconds
Complete requests:      10000
Failed requests:        0
Total transferred:      52630000 bytes
HTML transferred:       47660000 bytes
Requests per second:    2836.56 [#/sec] (mean)
Time per request:       7.051 [ms] (mean)
Time per request:       0.353 [ms] (mean, across all concurrent requests)
Transfer rate:          14578.94 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.0      0       0
Processing:     1    7   3.9      7      17
Waiting:        1    7   3.9      7      17
Total:          1    7   3.9      7      17

Percentage of the requests served within a certain time (ms)
  50%      7
  66%      9
  75%     10
  80%     11
  90%     12
  95%     13
  98%     13
  99%     14
 100%     17 (longest request)
```


ab log for getting JSON data:

```
ab -n 10000 -c 20 http://localhost:5460/json
This is ApacheBench, Version 2.3 <$Revision: 1706008 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 1000 requests
Completed 2000 requests
Completed 3000 requests
Completed 4000 requests
Completed 5000 requests
Completed 6000 requests
Completed 7000 requests
Completed 8000 requests
Completed 9000 requests
Completed 10000 requests
Finished 10000 requests


Server Software:        
Server Hostname:        localhost
Server Port:            5460

Document Path:          /json
Document Length:        3676 bytes

Concurrency Level:      20
Time taken for tests:   1.723 seconds
Complete requests:      10000
Failed requests:        0
Total transferred:      42410000 bytes
HTML transferred:       36760000 bytes
Requests per second:    5803.31 [#/sec] (mean)
Time per request:       3.446 [ms] (mean)
Time per request:       0.172 [ms] (mean, across all concurrent requests)
Transfer rate:          24035.01 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.0      0       0
Processing:     0    3   2.0      3      16
Waiting:        0    3   2.0      3      16
Total:          0    3   2.0      3      16

Percentage of the requests served within a certain time (ms)
  50%      3
  66%      4
  75%      5
  80%      5
  90%      6
  95%      6
  98%      7
  99%      7
 100%     16 (longest request)
```

ab log for getting CSV data:

```
ab -n 10000 -c 20 http://localhost:5460/csv
This is ApacheBench, Version 2.3 <$Revision: 1706008 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 1000 requests
Completed 2000 requests
Completed 3000 requests
Completed 4000 requests
Completed 5000 requests
Completed 6000 requests
Completed 7000 requests
Completed 8000 requests
Completed 9000 requests
Completed 10000 requests
Finished 10000 requests


Server Software:        
Server Hostname:        localhost
Server Port:            5460

Document Path:          /csv
Document Length:        1402 bytes

Concurrency Level:      20
Time taken for tests:   2.689 seconds
Complete requests:      10000
Failed requests:        0
Total transferred:      19590000 bytes
HTML transferred:       14020000 bytes
Requests per second:    3718.64 [#/sec] (mean)
Time per request:       5.378 [ms] (mean)
Time per request:       0.269 [ms] (mean, across all concurrent requests)
Transfer rate:          7114.07 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.0      0       0
Processing:     0    5   3.2      5      20
Waiting:        0    5   3.2      5      20
Total:          0    5   3.2      5      20

Percentage of the requests served within a certain time (ms)
  50%      5
  66%      7
  75%      8
  80%      8
  90%      9
  95%     10
  98%     13
  99%     15
 100%     20 (longest request)
```

ab log for getting XLS report:

```
ab -n 1000 -c 5 http://localhost:5460/xls
This is ApacheBench, Version 2.3 <$Revision: 1706008 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 100 requests
Completed 200 requests
Completed 300 requests
Completed 400 requests
Completed 500 requests
Completed 600 requests
Completed 700 requests
Completed 800 requests
Completed 900 requests
Completed 1000 requests
Finished 1000 requests


Server Software:        
Server Hostname:        localhost
Server Port:            5460

Document Path:          /xls
Document Length:        26624 bytes

Concurrency Level:      5
Time taken for tests:   2.218 seconds
Complete requests:      1000
Failed requests:        0
Total transferred:      27215000 bytes
HTML transferred:       26624000 bytes
Requests per second:    450.88 [#/sec] (mean)
Time per request:       11.089 [ms] (mean)
Time per request:       2.218 [ms] (mean, across all concurrent requests)
Transfer rate:          11983.08 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.0      0       0
Processing:     3   11   5.5     12      49
Waiting:        3   11   5.5     12      49
Total:          3   11   5.5     12      49

Percentage of the requests served within a certain time (ms)
  50%     12
  66%     13
  75%     16
  80%     16
  90%     17
  95%     19
  98%     20
  99%     21
 100%     49 (longest request)
```

ab log for getting XLSX report

```
ab -n 1000 -c 5 http://localhost:5460/xlsx
This is ApacheBench, Version 2.3 <$Revision: 1706008 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 100 requests
Completed 200 requests
Completed 300 requests
Completed 400 requests
Completed 500 requests
Completed 600 requests
Completed 700 requests
Completed 800 requests
Completed 900 requests
Completed 1000 requests
Finished 1000 requests


Server Software:        
Server Hostname:        localhost
Server Port:            5460

Document Path:          /xlsx
Document Length:        11125 bytes

Concurrency Level:      5
Time taken for tests:   12.647 seconds
Complete requests:      1000
Failed requests:        0
Total transferred:      11758000 bytes
HTML transferred:       11125000 bytes
Requests per second:    79.07 [#/sec] (mean)
Time per request:       63.237 [ms] (mean)
Time per request:       12.647 [ms] (mean, across all concurrent requests)
Transfer rate:          907.89 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.0      0       0
Processing:    13   63  30.1     68     185
Waiting:       12   62  30.0     66     183
Total:         13   63  30.1     68     185

Percentage of the requests served within a certain time (ms)
  50%     68
  66%     75
  75%     92
  80%     93
  90%    100
  95%    110
  98%    124
  99%    135
 100%    185 (longest request)
```

 
## FAQ

**Question**: Why do I get the following error message ?

`Cannot encrypt/decrypt! Please download Java Crypto Extension pack from Oracle`

**Answer**: Please download and install the JCE pack from Oracle for
[Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html) or 
[Java 7](http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html)
