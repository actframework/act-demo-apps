package demo.todo.beetlsql;

import static act.controller.Controller.Util.notFoundIfNull;

import act.Act;
import act.db.beetlsql.BeetlSqlTransactional;
import act.db.sql.tx.Transactional;
import org.osgl.http.H;
import org.osgl.mvc.annotation.*;

import javax.inject.Inject;

/**
 * A Simple Todo application controller
 */
@SuppressWarnings("unused")
@With(BeetlSqlTransactional.class)
public class Todo {

    @Inject
    private TodoItem.Mapper mapper;

    @GetAction
    public void home() {}

    @GetAction("/list")
    public Iterable<TodoItem> list(String q) {
    		// mapper.all();
        return mapper.all();
    }

    @PostAction("/list")
    @Transactional
    public void post(String desc) {
        TodoItem item = new TodoItem();
        item.setDesc(desc);
        mapper.insert(item);
    }

    @DeleteAction("/list/{id}")
    public void delete(int id) {
        TodoItem item = mapper.single(id);
        mapper.deleteById(id);
    }

    @PutAction("/list/{id}")
    public void update(int id, String desc) {
        TodoItem item = mapper.single(id);
        notFoundIfNull(item);
        item.setDesc(desc);
        mapper.updateById(item);
    }

    @PostAction("/create")
    @ResponseContentType(H.MediaType.JSON)
    public TodoItem testCreate(TodoItem todo) {
        return todo;
    }

    @GetAction("/list/{id}")
    public TodoItem showItem(long id) {
        TodoItem item = mapper.single(id);
        notFoundIfNull(item);
        return item;
    }
    

    
    public static void main(String[] args) throws Exception {
        Act.start("TODO-BeetlSql");
    }


}
