package demo.jobs;

/*-
 * #%L
 * actframework app demo - job scheduling
 * %%
 * Copyright (C) 2018 ActFramework
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import act.cli.Command;
import act.cli.Optional;
import act.util.PropertySpec;
import org.osgl.logging.LogManager;
import org.osgl.logging.Logger;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.util.C;

import java.util.List;

public class JobLog {
    private static final Logger logger = LogManager.get(JobLog.class);
    private static C.List<String> logs = C.newList();
    public static void log(String event) {
        logger.info(event);
        logs.add(event);
    }

    @Command(name = "log.list", help = "List job logs")
    @PropertySpec("this as log")
    @GetAction("/log")
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
