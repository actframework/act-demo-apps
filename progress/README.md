# Progress Demo

This project demonstrate how to track backend job progress (new feature introduced in Act-1.5.0)

## Track async command executing status

There are two ways to keep track of the async command executing status:

### Using the job ID to track the status

Sample code 

```java
@Async
@Command(name = "countdown", help = "countdown the number in background")
public void countdown(
        @Required("specify the number") int number,
        ProgressGauge progressGauge
) throws Exception {
    progressGauge.updateMaxHint(number);
    for (int i = 0; i < number; ++i) {
        progressGauge.step();
        Thread.sleep(500);
    }
}
```

When running the `countdown` command it will return a job ID:

```
progress[2k9b0do8]>countdown 100
countdown 100
Async job started: 2k9b0do9BUDB
```

Then it can use the job ID to track the job progress:

```
progress[2k9b0do8]>job.progress 2k9b0do9BUDB
job.progress 2k9b0do9BUDB
67
```

The number `67` printed above is the percentage of the job progress

### Use progress bar to track the status

Sample code:

```java
@Async
@ReportProgress
@Command(name = "countdown.track", help = "countdown the number and track")
public void countdownAndReportProgress(
        @Required("specify the number") int number,
        ProgressGauge progressGauge
) throws Exception {
    progressGauge.updateMaxHint(number);
    for (int i = 0; i < number; ++i) {
        progressGauge.step();
        Thread.sleep(200);
    }
}
```

The only thing different with the first Java source is that we have annotation `@ReportProgress` on this one, and the framework will block the CLI and display the progress bar:

```
progress[2k9b0do8]>countdown.track 100
countdown.track 100
act.progress.capFirst  65% │████████████▏      │  64/100 (0:00:12 / 0:00:07) 
``` 

