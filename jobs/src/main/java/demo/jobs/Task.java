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

import act.controller.annotation.UrlContext;
import act.util.Async;
import act.util.ProgressGauge;
import org.osgl.mvc.annotation.PostAction;

import java.util.Random;

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
