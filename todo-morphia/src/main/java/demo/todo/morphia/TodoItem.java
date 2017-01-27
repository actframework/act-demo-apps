package demo.todo.morphia;

import act.db.morphia.MorphiaModel;
import org.mongodb.morphia.annotations.Entity;

@Entity("todo")
public class TodoItem extends MorphiaModel<TodoItem> {

    public String desc;

    public TodoItem(String desc) {
        this.desc = desc;
    }
}
