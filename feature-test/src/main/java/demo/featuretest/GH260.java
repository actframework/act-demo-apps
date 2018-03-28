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
import act.util.FastJsonFeature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import demo.featuretest.gh251.Foo;
import org.osgl.mvc.annotation.GetAction;

@UrlContext("260")
public class GH260 extends GHTest {


    @FastJsonFeature(SerializerFeature.DisableCircularReferenceDetect)
    @GetAction
    public Foo foo() {
        return new Foo();
    }

}
