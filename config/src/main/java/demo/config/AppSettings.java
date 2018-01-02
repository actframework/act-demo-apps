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
import org.osgl.$;
import org.osgl.util.Const;

// AutoConfig automatically load configuration items with prefix specified
// The default prefix is "app.", thus @AutoConfig equals to @AutoConfig("app")
@AutoConfig
public class AppSettings {

    // Style one: CAPITALS separated by underscore
    public static final Const<Integer> FOO_BAR = $.constant(0);
    public static final Const<String> FOO_ZEE = $.constant("");
    public static final Const<String> FOO_AUTH_CODE = $.constant("xyz");

    // Style two: embedded class
    public static class db {
        public static Const<String> host = $.constant();
        public static Const<String> port = $.constant();
        public static Const<String> db = $.constant();
    }

    // Style two: embedded class
    public static class git {
        public static Const<String> protocol = $.constant();
        public static Const<String> repository = $.constant();
        public static Const<String> username = $.constant();
        public static Const<String> password = $.constant();
    }
}
