package act.fsa.todo_morphia;

import act.boot.app.RunApp;
import act.db.morphia.MorphiaDao;
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
public class TodoMorphiaApp {

    @GetAction("/")
    public Result home() {
        return render();
    }

    @GetAction("/list")
    public List<TodoItem> list() {
        return C.list(TodoItem.dao().findAll());
    }

    @PostAction("/list")
    public void add(String desc) {
        TodoItem item = new TodoItem(desc);
        TodoItem.dao().save(item);
    }

    @DeleteAction("/list/{id}")
    public void delete(String id) {
        TodoItem.dao().deleteById(new ObjectId(id));
    }

    @PutAction("/list/{id}")
    public void update(String id, String desc) {
        MorphiaDao<TodoItem> dao = TodoItem.dao();
        TodoItem item = dao.findById(new ObjectId(id));
        item.setDesc(desc);
        dao.save(item);
    }

    public static void main(String[] args) throws Exception {
        RunApp.start(TodoMorphiaApp.class);
    }

}
