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
import act.inject.DefaultValue;
import act.metric.MeasureTime;
import org.osgl.mvc.annotation.GetAction;

@UrlContext("482")
public class GH482 extends GHTest{

    @GetAction("pi")
    public double pi(@DefaultValue("100") Integer len) {
        return calculatePi(len);
    }

    @MeasureTime("pi")
    private static double calculatePi(int len) {
        double pi = 4;
        boolean plus = false;
        for (int i = 3; i < len; i += 2) {
            if (plus) {
                pi += 4.0 / i;
            } else {
                pi -= 4.0 / i;
            }
            plus = !plus;
        }
        return pi;
    }

}
