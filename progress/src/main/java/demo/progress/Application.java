package demo.progress;

/*-
 * #%L
 * progress
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

import act.Act;
import act.cli.Command;
import act.cli.ReportProgress;
import act.cli.Required;
import act.util.Async;
import act.util.ProgressGauge;
import org.osgl.mvc.annotation.GetAction;

@SuppressWarnings("unused")
public class Application {

    @GetAction
    public void home() {
    }

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

    public static void main(String[] args) throws Exception {
        Act.start();
    }
}
