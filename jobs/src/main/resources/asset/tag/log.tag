<log>
    <ol id="job-log">
        <li each='{log in logs}'>{log}</li>
    </ol>
    <script>
    var self = this
    self.logs = []
    self.on('mount', function() {
        self.timer = setInterval(showLog, 5000)
    })
    self.on('unmount', function() {
        if (self.timer) {clearInterval(self.timer)}
    });
    var showLog = function() {
        $.getJSON("/log", function(data) {
            self.logs = data
            self.update()
        })
    };
    </script>
</log>