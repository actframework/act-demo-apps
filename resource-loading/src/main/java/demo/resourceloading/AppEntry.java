package demo.resourceloading;

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
import act.inject.util.LoadConfig;
import act.inject.util.LoadResource;
import act.util.JsonView;
import org.osgl.mvc.annotation.GetAction;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class AppEntry {

    @LoadResource("foo.json")
    private Foo fromJson;

    @LoadConfig("foo.yml")
    private Foo fromYaml;

    @LoadResource("int_list.txt")
    private int[] intList;

    @LoadResource("list.json")
    private List<Foo> listFromJson;

    @LoadResource("list.yml")
    private List<Foo> listFromYaml;

    @LoadResource("double.matrix")
    private double[][] dblMatrix;

    @LoadResource("map.properties")
    private Map<String, String> map;

    @GetAction
    public void home() {}

    @GetAction("/foo/json")
    @JsonView
    public Foo fooFromJson() {
        return fromJson;
    }

    @GetAction("/foo/yaml")
    @JsonView
    public Foo fooFromYaml() {
        return fromYaml;
    }

    @GetAction("/list/json")
    @JsonView
    public List<Foo> listFromJson() {
        return listFromJson;
    }

    @GetAction("/list/yaml")
    @JsonView
    public List<Foo> listFromYaml() {
        return listFromYaml;
    }

    @GetAction("/int_list")
    @JsonView
    public int[] intList() {
        return intList;
    }

    @GetAction("/map")
    @JsonView
    public Map<String, String> map() {
        return map;
    }

    @GetAction("/double_matrix")
    @JsonView
    public double[][] doubleMatrix() {
        return dblMatrix;
    }

    public static void main(String[] args) throws Exception {
        Act.start();
    }

}
