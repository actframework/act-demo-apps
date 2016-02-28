package act.fsa.todo_morphia;

import act.db.morphia.MorphiaModel;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;

@Entity("todo")
public class TodoItem extends MorphiaModel<TodoItem> {

    private String desc;

    private TodoItem() {}

    public TodoItem(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getDescription() {
        return getDesc();
    }

    public TodoItem setDesc(String desc) {
        this.desc = desc;
        return this;
    }

}
