package demo.featuretest.multi_db;

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
import act.db.DB;
import act.db.morphia.MorphiaAdaptiveRecord;
import act.db.morphia.MorphiaDao;
import org.mongodb.morphia.annotations.Entity;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.util.S;

@DB("db1")
@Entity("m1")
public class ModelOne extends MorphiaAdaptiveRecord<ModelOne> {
    public String name;
    public ModelOne() {
        this.name = S.random();
    }

    @UrlContext("/mdb/m1")
    public static class Dao extends MorphiaDao<ModelOne> {

        @PostAction
        public ModelOne create() {
            return save(new ModelOne());
        }

    }
}
