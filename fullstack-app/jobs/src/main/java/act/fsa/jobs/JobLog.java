package act.fsa.jobs;

import act.cli.Command;
import act.cli.HelpMsg;
import act.cli.Optional;
import act.util.DataView;
import org.osgl.logging.LogManager;
import org.osgl.logging.Logger;
import org.osgl.util.C;

import java.util.List;

public class JobLog {
    private static final Logger logger = LogManager.get(JobLog.class);
    private static C.List<String> logs = C.newList();
    public static void log(String event) {
        logger.info(event);
        logs.add(event);
    }

    @Command("log.list")
    @HelpMsg("List job logs")
    @DataView("this as log")
    public static List<String> logs(
            @Optional(help = "limit the lines returned") Integer limit
    ) {
        if (null != limit && limit > 0) {
            return logs.take(limit);
        } else {
            return C.list(logs);
        }
    }
}
