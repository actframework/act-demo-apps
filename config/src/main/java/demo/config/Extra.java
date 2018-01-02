package demo.config;

/*-
 * #%L
 * actframework app demo - configuration
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

import act.app.conf.AutoConfig;
import act.inject.DefaultValue;
import org.osgl.$;
import org.osgl.util.Const;
import org.osgl.util.S;
import org.osgl.inject.annotation.Configuration;

/**
 * A demo controller which is mapped by routes.conf file
 */
@AutoConfig("x")
public class Extra {

    private static final Const<String> TO = $.constant();

    @Configuration("x.to")
    private static String to;

    public String hi(@DefaultValue("body") String who) {
        return S.fmt("Hi %s, how are you going?", who);
    }

    public String bye() {
        return S.fmt("%s, see you soon!", TO.get());
    }

}
