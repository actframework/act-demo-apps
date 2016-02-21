package act.fsa.todo_ebean;

import act.boot.app.RunApp;
import act.db.ebean.EbeanDao;
import org.osgl.logging.L;
import org.osgl.logging.Logger;
import org.osgl.mvc.annotation.DeleteAction;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.annotation.PutAction;
import org.osgl.mvc.result.Result;

import javax.inject.Inject;
import java.util.List;

import static act.controller.Controller.Util.ok;
import static act.controller.Controller.Util.render;

/**
 * A Simple Todo application controller
 */
public class TodoEbeanApp {

    static Logger logger = L.get(TodoEbeanApp.class);

    @Inject
    private TodoItem.Dao dao;

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
        RunApp.start(TodoEbeanApp.class);
    }


}
