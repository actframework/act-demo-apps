package act.fsa.todo_ebean;

import act.boot.app.RunApp;
import act.job.Every;
import org.avaje.agentloader.AgentLoader;
import org.osgl.logging.L;
import org.osgl.logging.Logger;
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
public class TodoEbeanApp {

    static Logger logger = L.get(TodoEbeanApp.class);

    @Inject
    private TodoItemDao dao;

    @GetAction("/")
    public Result home() {
        return render();
    }

    @GetAction("/list")
    public Result list() {
        List<TodoItem> list = dao.findAllAsList();
        return render(list);
    }

    @PostAction("/list")
    public Result post(String desc) {
        TodoItem item = new TodoItem(desc);
        dao.save(item);
        return ok();
    }

    @DeleteAction("/list/{id}")
    public Result delete(long id) {
        TodoItem item = dao.findById(id);
        dao.delete(item);
        return ok();
    }

    @PutAction("/list/{id}")
    public Result update(long id, String desc) {
        TodoItem item = dao.findById(id);
        item.setDesc(desc);
        dao.save(item);
        return ok();
    }

    public static void main(String[] args) throws Exception {
        RunApp.start(TodoEbeanApp.class);
    }


}
