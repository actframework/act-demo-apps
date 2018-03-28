package demo.featuretest;

/*-
 * #%L
 * actframework Feature Test App
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
import act.cli.ReportProgress;
import act.cli.Required;
import act.util.Async;
import act.util.ProgressGauge;

public class GH266 {
    @Async
    @Command("countdown.async")
    public void testAsncCommand(
            @Required("specify the number") int num,
            ProgressGauge progressGauge
    ) throws Exception {
        progressGauge.updateMaxHint(num);
        for (int i = 0; i < num; ++i) {
            progressGauge.step();
            Thread.sleep(500);
        }
    }

    @Async
    @ReportProgress(type = ReportProgress.Type.TEXT)
    @Command("countdown")
    public void countDown(
            @Required("specify the number") int num,
            ProgressGauge progressGauge
    ) throws Exception {
        progressGauge.updateMaxHint(num);
        for (int i = 0; i < num; ++i) {
            progressGauge.step();
            Thread.sleep(500);
        }
    }
}
