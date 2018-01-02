package demo.todo.ebean;

/*-
 * #%L
 * actframework app demo - TODO (ebean)
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
import act.db.ebean2.EbeanDao;
import act.util.SimpleBean;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Entity(name = "ticket")
public class Ticket implements SimpleBean {

    @Id
    public Long id;

    public String name;

    public Date date;

    @UrlContext("/ticket")
    public static class Dao extends EbeanDao<Long, Ticket> {

        @GetAction
        public Iterable<Ticket> list() {
            return findAll();
        }

        @PostAction
        public Ticket create(Ticket ticket) {
            return save(ticket);
        }

    }

}
