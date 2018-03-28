package demo.featuretest.gh193;

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
import act.db.DbBind;
import act.db.morphia.MorphiaAdaptiveRecord;
import act.db.morphia.MorphiaDao;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.osgl.$;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PatchAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.annotation.PutAction;
import org.osgl.util.E;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity("emp")
public class Employee extends MorphiaAdaptiveRecord<Employee> {

    public String firstName;
    public String lastName;
    public Address address;
    public List<ObjectId> idList = new ArrayList<>();

    @UrlContext("/gh193/employees")
    public static class Service extends MorphiaDao<Employee> {

        @GetAction
        public Iterable<Employee> list() {
            return findAll();
        }

        @GetAction("{id}")
        public Employee show(@DbBind("id") @NotNull Employee employee) {
            return employee;
        }

        @PostAction
        public Employee create(Employee employee) {
            return save(employee);
        }

        @PatchAction("{id}")
        public Employee replace(Employee employee, ObjectId id) {
            E.illegalArgumentIf($.ne(employee._id(), id));
            return save(employee);
        }

        @PutAction("{id}")
        public Employee update(@DbBind("id") @NotNull Employee employee, Map<String, Object> updates) {
            employee.putValues(updates);
            return save(employee);
        }

    }


}
