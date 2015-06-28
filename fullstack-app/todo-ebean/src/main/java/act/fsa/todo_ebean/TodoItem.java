package act.fsa.todo_ebean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "todo")
public class TodoItem {
    @Id
    private long _id;
    private String desc;

    private TodoItem() {}

    public TodoItem(String desc) {
        this.desc = desc;
    }

    public long getId() {
        return _id;
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
