package demo.todo.hibernate;

/*-
 * #%L
 * actframework app demo - TODO (hibernate)
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

import org.osgl.util.S;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "todo")
public class TodoItem extends ModelBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long _id;
    private String desc;

    private TodoItem() {
    }

    public TodoItem(String desc) {
        this.desc = desc;
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        _id = id;
    }

    public String getDesc() {
        return desc;
    }

    public TodoItem setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    @Override
    public String toString() {
        return S.fmt("%3s|%s", getId(), getDesc());
    }
}
