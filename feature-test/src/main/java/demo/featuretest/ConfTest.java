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

import act.app.conf.AutoConfig;
import act.app.data.StringValueResolverManager;
import act.conf.AppConfig;
import act.controller.annotation.UrlContext;
import org.osgl.$;
import org.osgl.http.H;
import org.osgl.inject.annotation.Configuration;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.ResponseContentType;
import org.osgl.util.Const;
import org.osgl.util.S;

import java.util.List;
import java.util.Map;

@UrlContext("/conf")
@AutoConfig("myconf")
@ResponseContentType(H.MediaType.JSON)
public class ConfTest {

    @Configuration("greet.default")
    private GreetingService defaultService;

    @Configuration("greet")
    private Map<String, GreetingService> greetingServiceMap;

    @GetAction("greet")
    public String greetDefault() {
        return defaultService.greet();
    }

    @GetAction("greet/all")
    public Object allGreetings() {
        return greetingServiceMap;
    }

    @Configuration("greets")
    private List<GreetingService> greetingServices;

    @GetAction("greet/list")
    public Object greetingList() {
        return greetingServices;
    }

    @GetAction("greet/{scenario}")
    public String greetScenario2(int scenario) {
        return greetingServiceMap.get("scenario" + scenario).greet();
    }

    private static final Const<Integer> FOO_BAR = $.constant();

    @Configuration("myconf.foo.bar")
    private int fooBar;

    @GetAction("pull")
    public int pull(AppConfig conf, StringValueResolverManager resolver) {
        Object o = conf.get("myconf.foo.bar");
        return resolver.resolve(S.string(o), int.class);
    }

    @GetAction("auto_conf")
    public int autoConf() {
        return FOO_BAR.get();
    }

    @GetAction("inject")
    public int inject() {
        return fooBar;
    }

    @GetAction("inject_param")
    public int injectParam(@Configuration("myconf.foo.bar") int fooBar) {
        return fooBar;
    }

    @GetAction("map")
    public Object barMap(@Configuration("myconf.foo.bar") Map<String, Integer> barMap) {
        return barMap;
    }

    @GetAction("map2")
    public Object fooMap(@Configuration("myconf.foo") Map<String, Integer> fooMap) {
        return fooMap;
    }

    @GetAction("listDemo")
    public Object listDemo(@Configuration("myconf.list.demo") int[] list) {
        return list;
    }
}
