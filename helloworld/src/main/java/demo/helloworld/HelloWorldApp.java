package demo.helloworld;

/*-
 * #%L
 * actframework app demo - hello world
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
import act.inject.DefaultValue;
import act.metric.MeasureTime;
import act.metric.MetricContext;
import act.util.JsonView;
import act.util.LogSupport;
import act.util.Output;
import org.osgl.inject.annotation.Provided;
import org.osgl.mvc.annotation.GetAction;

import java.util.Map;

/**
 * The simple hello world app.
 * <p>Run this app, try to update some of the code, then
 * press F5 in the browser to watch the immediate change
 * in the browser!</p>
 */
@SuppressWarnings("unused")
@MetricContext("app")
public class HelloWorldApp extends LogSupport {

    @GetAction
    //@NonBlock
    //@NoImplicitTemplateVariable
    //@SessionFree
    public void home(@DefaultValue("World") @Output String who) {
    }

    @GetAction("/txt")
    //@NonBlock
    //@NoImplicitTemplateVariable
    //@SessionFree
    public String text(@Provided String x) {
        return "Hello World";
    }

    @GetAction("/enc/{pass}")
    public String testEnc(String pass) {
        String hash = Act.crypto().passwordHash(pass);
        boolean verified = Act.crypto().verifyPassword(pass, hash);
        info("\n\tpass: %s\n\thash: %s\n\tverified: %s", pass, hash, verified);
        return hash;
    }

    @GetAction("/enc/verify")
    public boolean testDec(String hash, String pass) {
        return Act.crypto().verifyPassword(pass, hash);
    }

    @GetAction("x")
    @JsonView
    public Object x(Map<String, String> bar) {
        return bar;
    }

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

    public static void main(String[] args) throws Exception {
        Act.start();
    }

}
