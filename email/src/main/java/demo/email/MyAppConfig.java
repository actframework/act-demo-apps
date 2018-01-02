package demo.email;

/*-
 * #%L
 * actframework app demo - email
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

import act.app.conf.AppConfigurator;

public class MyAppConfig extends AppConfigurator<MyAppConfig> {
    @Override
    public void configure() {
        prop("mailer", "notification");
        prop("mailer.notification.to", "user1@xxx.com, user2@xxx.com");
        prop("mailer.notification.subject", "notification");
    }
}
