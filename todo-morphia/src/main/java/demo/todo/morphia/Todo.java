package demo.todo.morphia;

/*-
 * #%L
 * actframework app demo - TODO (mongodb)
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

import static act.controller.Controller.Util.render;

import act.Act;
import act.db.DbBind;
import org.osgl.mvc.annotation.DeleteAction;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.annotation.PutAction;
import org.osgl.mvc.result.Result;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

/**
 * A Simple Todo application controller
 */
public class Todo {

    @Inject
    private TodoItem.Dao dao;

    @GetAction
    public Result home() {
        return render();
    }

    @GetAction("/list")
    public Iterable<TodoItem> list() {
        return dao.q();
    }

    @PostAction("/list")
    public void add(String desc) {
        TodoItem item = new TodoItem(desc);
        dao.save(item);
    }

    @PostAction("/test")
    public TodoItem submit(TodoItem todo) {
        return todo;
    }

    /**
     * So we use the annotation `@DbBind` to specify that parameter `item`
     * should be bound to a database record by ID, and the value of the ID
     * is found by URL parameter named `no`.
     */
    @GetAction("/list/{no}")
    public TodoItem showItem(@DbBind("no") TodoItem item) {
        return item;
    }

    /**
     * Here we use `@DbBind` again to bind URL path variable named `item`
     * to a database record. The value of `item` is used as the key to
     * look for the record. Because the URL path variable name `item`
     * is exactly the same as the method parameter item, so we don't need
     * to specify the bind name as `@DbBind("item")`, which is how we did
     * for the {@link #showItem(TodoItem)} method. So this is tricky on
     * how to name your URL path variable. If you name it the same as your
     * binding parameter name, then you save passing value to the `@DbBind`
     * annotation
     *
     * The `@NotNull` annotation means if we can't find an item from
     * database by the `no` specified, then it shall respond to the request
     * with 404 Not Found. Note we didn't specify the `@NotNull` annotation
     * in the {@link #showItem(TodoItem)} method because it will anyway
     * return the `item` entity back and if it is `null`, the framework
     * will automatically respond with 404 Not Found
     */
    @PutAction("/list/{item}")
    public void update(@DbBind @NotNull TodoItem item, String desc) {
        item.desc = desc;
        dao.save(item);
    }

    @DeleteAction("/list/{id}")
    public void delete(String id) {
        dao.deleteById(id);
    }

    public static void main(String[] args) throws Exception {
        System.setProperty("http.port", "8888");
        System.setProperty("cli.port", "8889");
        Act.start("TODO-Morphia");
    }

}
