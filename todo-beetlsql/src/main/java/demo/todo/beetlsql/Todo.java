package demo.todo.beetlsql;

import static act.controller.Controller.Util.notFoundIfNull;

import javax.inject.Inject;

import org.beetl.sql.core.OnConnection;
import org.osgl.mvc.annotation.DeleteAction;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.annotation.PutAction;

import act.Act;

/**
 * A Simple Todo application controller
 */
@SuppressWarnings("unused")
public class Todo {

    @Inject
    private TodoItemMapper mapper;

    @GetAction
    public void home() {}

    @GetAction("/list")
    public Iterable<TodoItem> list(String q) {
    		// mapper.all();
        return mapper.selectAll();
    }

    @PostAction("/list")
    public void post(String desc) {
        TodoItem item = new TodoItem();
        item.setDesc(desc);
        mapper.insert(item);
    }

    @DeleteAction("/list/{id}")
    public void delete(long id) {
        TodoItem item = mapper.single(id);
        mapper.deleteById(id);
    }

    @PutAction("/list/{id}")
    public void update(long id, String desc) {
        TodoItem item = mapper.single(id);
        notFoundIfNull(item);
        item.setDesc(desc);
        mapper.updateById(item);
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
