# Actframework Job demo

This sample project demonstrates how to schedule backend jobs in actframework

## Start the app

There are two ways to run the app:

* Run `demo.jobs.JobApp` directly in your IDE (you have to import the project as maven project) into your IDE
* type `mvn clean compile exec:exec` in the project dir

Now you can navigate the browser to http://localhost:5460 and play around with it.

## Application explained

The app demonstrates two folds of Job support in ActFramework:

1. Creating background Jobs
2. Handling long-run http request handler

###  Creating background jobs

In ActFramework it is super easy to create background jobs, just write the logic in a method and put on relevant Job annotation. For example, the `AppEntry` class has defined two job methods that starts with application in a synchronous or asynchrouns way:

```java
    @OnAppStart(async = true)
    public void onAppStartAsync() {
        JobLog.log("onAppStartAsync called");
    }

    @OnAppStart
    public static void onAppStartSync() {
        JobLog.log("onAppStartSync called");
    }
```

More job methods could be found in the `SomeService` class:

```java
    /**
     * This method will get called every x, where
     * `x` is configured through `every.check_status`
     * configuration
     */
    @Every(value = "every.check_status", id = "CHECK_STATUS")
    @Env.Group("job")
    public void checkStatus() {
        JobLog.log("SomeService.checkStatus");
    }

    /**
     * The method is invoked by framework automatically before invocation of
     * {@link #checkStatus()} method
     */
    @InvokeBefore("CHECK_STATUS")
    public void beforeCheckingStatus() {
        JobLog.log("Before checking status");
    }

    /**
     * This method will get started along with Application start up
     */
    @OnAppStart(id = "PREPARE-SOME_SERVICE")
    public void prepare() {
        JobLog.log("SomeService.prepare");
    }

    /**
     * This method will get invoked after {@link #prepare()} method is invoked.
     * <p>Note the method name {@code afterPrepare} doesn't matter, it could be any
     * other name.</p>
     */
    @InvokeAfter("PREPARE-SOME_SERVICE")
    public void afterPrepare() {
        JobLog.log("SomeService.afterPrepare");
    }

    /**
     * This method is scheduled to run every minute
     */
    @Cron("0 43 * * * ?")
    public void backup() {
        JobLog.log("SomeService.backup");
    }

    /**
     * This method is scheduled to run as per value of {@code cron.passwordReminder}
     * configuration. See {@code /resources/conf/app.properties}
     */
    @Cron("cron.passwordReminder")
    public void checkAndSendOutPasswordReminder() {
        JobLog.log("checking password expiration and sending out password reminder");
    }

    /**
     * The method get invoked when ActFramework's {@link act.event.EventBus} is initialized
     */
    @OnAppEvent(AppEventId.EVENT_BUS_INITIALIZED)
    public static void onAppEventBusInitialized() {
        JobLog.log("onAppEventBusInitialized called");
    }

    /**
     * The method get invoked asynchronously when ActFramework's {@link act.event.EventBus} is initialized
     */
    @OnAppEvent(value = AppEventId.EVENT_BUS_INITIALIZED, async = true)
    public static void onAppEventBusInitializedAsync() {
        JobLog.log("onAppEventBusInitializedAsync called");
    }
```

In this demo application, it logs a message when the job method get called. All logs can be accessed by clicking on the `Logs` menu in the left navigation pane when landing at the http://localhost:5460 page

### Handling long-run http request handler

The demo project also shows how to deal with long run HTTP request handler via the `Task` class:

```java
@UrlContext("/task")
public class Task {

    /**
     * This method simulate a long time task that should run in background.
     *
     * @param gauge
     *      framework injected progress gauge, developer can update task progress
     *      using this helper
     * @throws Exception
     *      any exception raised during executing the long-time task
     */
    @Async
    @PostAction
    public void create(ProgressGauge gauge) throws Exception {
        int duration = randomDuration();
        gauge.updateMaxHint(duration);
        while (duration-- > 0) {
            gauge.step();
            randomError();
            Thread.sleep(10 * (new Random().nextInt(10) +  1));
        }
    }

    private static void randomError() {
        int n = new Random().nextInt(200);
        if (n % 197 == 0) {
            throw new RuntimeException();
        }
    }

    private static int randomDuration() {
        return 5 + new Random().nextInt(200);
    }

}
```

As shown above the `Task.create` method is a long run handler. The way to deal with it is to add a `Async` annotation, in which case framework will return immediately without waiting for the handler to finish. Framework will return a job id that is associated with the handler. But we are not using that in the demo app, instead of rely on other facility provided by ActFramework to get the progress of the job execution: websocket.

The application is declared to have a websocket connection at `/ws` with `@WsEndpoint` annotation on `AppEntry` class:

```java
@WsEndpoint("/ws")
public class AppEntry {
    
    ......

    public static void main(String[] args) throws Exception {
        Act.start("Job Demo");
    }

}
```

ActFramework will automatically link the current session with the job status tag (that contains a job ID). Whenever there are any progress in the the job execution, the progress will be published to the relevant job status tag. Thus the client just need to connect to the websocket and it will automatically get updated when there are progress in the job creation. The logic can be found in the `progress.tag` file:

```javascript
    self.ws = $.createWebSocket('/ws')
    self.ws.onmessage = function (event) {
        var gauge = JSON.parse(event.data).act_job_progress
        if (!gauge) return
        if (gauge.progressPercent >= 100 || gauge.destroyed) {
            _.remove(self.jobs, function(job) {return job.jobId === gauge.id})
            self.update()
            return
        }
        var $prog = $('progress[data-id="' + gauge.id + '"]')
        var progress = $prog.get(0);
        if (progress) {
            progress.value = gauge.currentSteps
            progress.max = gauge.maxHint
        }
    }
```

To play with this demo, just hit the `Progress` menu in the left navigation pane, and click on `Launch a job` button to create an new long run job. The code snippet shown above will update the progress bar.

For more about Actframework job scheduling, please refers to

* http://actframework.org/doc/job