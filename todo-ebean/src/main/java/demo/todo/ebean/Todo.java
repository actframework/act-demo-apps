package demo.todo.ebean;

import act.Version;
import act.boot.app.RunApp;
import act.db.ebean.EbeanDao;
import org.osgl.logging.L;
import org.osgl.logging.Logger;
import org.osgl.mvc.annotation.DeleteAction;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.annotation.PutAction;

import javax.inject.Inject;
import java.util.List;

/**
 * A Simple Todo application controller
 */
public class Todo {

    static Logger logger = L.get(Todo.class);

    @Inject
    private EbeanDao<Long, TodoItem> dao;

    @GetAction
    public void home() {
    }

    @GetAction("/list")
    public List<TodoItem> list() {
        return dao.findAllAsList();
    }

    @PostAction("/list")
    public void post(String desc) {
        TodoItem item = new TodoItem(desc);
        dao.save(item);
    }

    @DeleteAction("/list/{id}")
    public void delete(long id) {
        TodoItem item = dao.findById(id);
        dao.delete(item);
    }

    @PutAction("/list/{id}")
    public void update(long id, String desc) {
        TodoItem item = dao.findById(id);
        item.setDesc(desc);
        dao.save(item);
    }

    public static void main(String[] args) throws Exception {
        RunApp.start("TODO", Version.appVersion(), Todo.class);
    }


}
