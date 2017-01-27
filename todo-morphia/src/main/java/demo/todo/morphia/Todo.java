package demo.todo.morphia;

import act.Version;
import act.boot.app.RunApp;
import act.db.morphia.MorphiaDao;
import org.bson.types.ObjectId;
import org.osgl.mvc.annotation.DeleteAction;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.annotation.PutAction;
import org.osgl.mvc.result.Result;

import javax.inject.Inject;

import static act.controller.Controller.Util.render;

/**
 * A Simple Todo application controller
 */
public class Todo {

    @Inject
    private MorphiaDao<TodoItem> dao;

    @GetAction("/")
    public Result home() {
        return render();
    }

    @GetAction("/list")
    public Iterable<TodoItem> list() {
        return dao.findAll();
    }

    @PostAction("/list")
    public void add(String desc) {
        TodoItem item = new TodoItem(desc);
        dao.save(item);
    }

    @DeleteAction("/list/{id}")
    public void delete(String id) {
        dao.deleteById(new ObjectId(id));
    }

    @PutAction("/list/{id}")
    public void update(String id, String desc) {
        TodoItem item = dao.findById(id);
        item.desc = desc;
        dao.save(item);
    }

    public static void main(String[] args) throws Exception {
        RunApp.start("TODO-Morphia", Version.appVersion(), Todo.class);
    }

}
