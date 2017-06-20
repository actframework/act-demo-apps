package demo.todo.ebean;

import org.osgl.util.S;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "todo")
public class TodoItem  {
    @Id
    private long _id;
    private String desc;

    private TodoItem() {
    }

    public TodoItem(String desc) {
        this.desc = desc;
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        _id = id;
    }

    public String getDesc() {
        return desc;
    }

    public TodoItem setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    @Override
    public String toString() {
        return S.fmt("%3s|%s", getId(), getDesc());
    }
}
