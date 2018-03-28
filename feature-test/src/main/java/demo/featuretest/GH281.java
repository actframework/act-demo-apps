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

import act.controller.annotation.UrlContext;
import act.data.annotation.DateFormatPattern;
import act.util.JsonView;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.osgl.mvc.annotation.GetAction;

import java.util.Date;

@UrlContext("281")
@JsonView
public class GH281 extends GHTest{

    public static class Foo {
        Date javaDate = new Date();
        DateTime jodaDate = DateTime.now();
        LocalTime jodaTime = LocalTime.now();
    }

    @GetAction
    @DateFormatPattern("MM-yyyy-dd")
    public Foo test() {
        return new Foo();
    }
}
