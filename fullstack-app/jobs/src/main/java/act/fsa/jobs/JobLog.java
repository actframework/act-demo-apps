package act.fsa.jobs;

import org.osgl.util.C;

import java.util.List;

public class JobLog {
    private static List<String> logs = C.newList();
    public static void log(String event) {
        logs.add(event);
    }
    public static List<String> logs() {
        return C.list(logs);
    }
}
