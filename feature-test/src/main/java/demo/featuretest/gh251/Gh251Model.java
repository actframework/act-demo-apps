package demo.featuretest.gh251;

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

import act.data.annotation.Data;
import act.util.SimpleBean;
import org.osgl.$;
import org.osgl.util.C;
import org.osgl.util.S;

import java.util.List;
import java.util.Locale;

@Data
public class Gh251Model implements SimpleBean {

    public String name;
    public Locale locale;
    public Foo[] foos;

    public Gh251Model() {
        this.name = S.random();
        this.locale = Locale.CHINA;
        if ($.random(true, false)) {
            this.foos = C.list(new Foo(), new Foo()).toArray(new Foo[2]);
        }
    }

    public static List<Gh251Model> randomList() {
        return C.list(new Gh251Model(), new Gh251Model());
    }
}
