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

import act.job.Every;

import javax.inject.Named;

public class InjectInParamDemo {

    @Every("50s")
    public void schedule(@Named("bar") DataProcessService processor) {
        processor.process("DI in param");
    }
}
