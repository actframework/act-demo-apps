package act.fsa.todo;

import act.boot.app.RunApp;
import org.bson.types.ObjectId;
import org.osgl.mvc.annotation.DeleteAction;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.annotation.PutAction;
import org.osgl.mvc.result.Result;
import org.osgl.util.C;

import javax.inject.Inject;
import java.util.List;

import static act.controller.Controller.Util.ok;
import static act.controller.Controller.Util.render;

/**
 * A Simple Todo application controller
 */
public class TodoApp {

    @Inject
    private TodoItemDao dao;

    @GetAction("/")
    public Result home() {
        return render();
    }

    @GetAction("/list")
    public Result list() {
        List<TodoItem> list = C.list(dao.findAll());
        return render(list);
    }

    @PostAction("/list")
    public Result add(String desc) {
        TodoItem item = new TodoItem(desc);
        dao.save(item);
        return ok();
    }

    @DeleteAction("/list/{id}")
    public Result delete(String id) {
        TodoItem item = dao.findById(new ObjectId(id));
        dao.delete(item);
        return ok();
    }

    @PutAction("/list/{id}")
    public Result update(String id, String desc) {
        TodoItem item = dao.findById(new ObjectId(id));
        item.setDesc(desc);
        dao.save(item);
        return ok();
    }

    public static void main(String[] args) throws Exception {
        RunApp.start(TodoApp.class);
    }

}
