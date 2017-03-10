# ActFramework Event Demo

This application demonstrates how to use event in ActFramework

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

After start the app you should see something like:

```
  _         _        ___     _    _         _  
 |_  \  /  |_  |\ |   |     | \  |_  |\/|  / \ 
 |_   \/   |_  | \|   |     |_/  |_  |  |  \_/ 
                                               
            powered by ActFramework R1.0.0-8707

 version: 1.0.0
scan pkg: demo.event
base dir: /home/luog/p/act/samples/event
     pid: 28535
 profile: dev
    mode: DEV

     zen: Simple is better than complex.

15:31:56.935 [main] INFO  a.Act - loading application(s) ...
15:31:56.945 [main] INFO  a.Act - App starting ....
15:31:57.063 [main] WARN  a.c.AppConfig - Application secret key not set! You are in the dangerous zone!!!
15:31:57.103 [main] WARN  a.a.DbServiceManager - DB service not initialized: No DB plugin found
15:31:57.849 [main] WARN  a.c.AppConfig - host is not configured. Use localhost as hostname
15:31:58.036 [main] WARN  a.c.b.HandlerEnhancer - local variable table info not found. ActionContext render args might not be automatically populated
15:31:58.067 [main] INFO  a.Act - App[Event Demo] loaded in 1122ms
15:31:58.088 [main] INFO  o.xnio - XNIO version 3.3.6.Final
15:31:58.102 [main] INFO  o.x.nio - XNIO NIO Implementation Version 3.3.6.Final
15:31:58.221 [main] INFO  a.Act - network client hooked on port: 5460
15:31:58.224 [main] INFO  a.Act - CLI server started on port: 5461
15:31:58.225 [main] INFO  a.Act - it takes 1822ms to start the app
```

Then you can go to the browser and navigate to `http://localhost:5460` to see the home page.

You should read the source code and check the console log to understand how event is dispatched synchronous and asynchronously