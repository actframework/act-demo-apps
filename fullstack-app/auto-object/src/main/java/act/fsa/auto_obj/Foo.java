package act.fsa.auto_obj;

import act.util.AutoObject;

@AutoObject
public class Foo {

    private int id;
    private String desc;
    private Bar bar;

    public Foo() {
        id = 10;
        desc = "foo" + id;
        bar = new Bar();
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

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }
}
