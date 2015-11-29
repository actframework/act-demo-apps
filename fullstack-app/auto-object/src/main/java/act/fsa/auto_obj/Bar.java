package act.fsa.auto_obj;

import act.util.AutoObject;

@AutoObject
public class Bar {
    private int id;
    private String desc;

    public Bar() {
        id = 5;
        desc = "bar" + id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
