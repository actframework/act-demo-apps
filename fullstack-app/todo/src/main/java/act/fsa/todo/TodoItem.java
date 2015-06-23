package act.fsa.todo;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("todo")
public class TodoItem {
    @Id
    private ObjectId _id;

    private String desc;

    private TodoItem() {}

    public TodoItem(String desc) {
        this.desc = desc;
    }

    public String getId() {
        return _id.toString();
    }

    public String getDesc() {
        return desc;
    }

    public TodoItem setDesc(String desc) {
        this.desc = desc;
        return this;
    }
}
