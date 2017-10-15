<prog>
    <button onclick='{launch}'>Launch a job</button>
    <div class='job' each='{job in jobs}'>
        <h4>Job[{job.jobId}]</h4>
        <progress data-id={job.jobId} style="width: 100%"></progress>
    </div>
    <script>
    var self = this
    self.jobs = []
    self.ws = $.createWebSocket('/ws')
    self.ws.onmessage = function (event) {
        var gauge = JSON.parse(event.data).act_job_progress
        console.log(gauge)
        if (gauge.progressPercent >= 100) {
            _.remove(self.jobs, function(job) {return job.jobId === gauge.id})
            self.update()
            return
        }
        var $prog = $('progress[data-id="' + gauge.id + '"]')
        var progress = $prog.get(0);
        if (progress) {
            progress.value = gauge.currentSteps
            progress.max = gauge.maxHint
        } else {
            console.log('unknown gauge: ' + gauge.id)
        }
    }
    launch() {
        $.postJSON('/task', function(data) {
            self.jobs.push({jobId: data.jobId})
            self.update()
        })
    }
    </script>
</prog>