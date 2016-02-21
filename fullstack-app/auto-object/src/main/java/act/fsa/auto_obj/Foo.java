package act.fsa.auto_obj;

import act.data.Data;
import act.util.EqualIgnore;
import org.osgl.util.S;

import java.util.Random;

@Data
public class Foo {

    public static final String DETAIL_VIEW = "-bar.id,bar.desc as bar_desc";

    private int id;

    private String desc;

    private Bar bar;

    @EqualIgnore
    private int r1; // will be ignored in equals and hashCode method

    private transient int r2; // will be ignored in equals and hashCode method

    public Foo() {
        id = 10;
        desc = "foo" + id;
        bar = new Bar();
        Random r = new Random();
        r1 = r.nextInt();
        r2 = r.nextInt();
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

    public int getR1() {
        return r1;
    }

    public void setR1(int r1) {
        this.r1 = r1;
    }

    public int getR2() {
        return r2;
    }

    public void setR2(int r2) {
        this.r2 = r2;
    }

    @Override
    public String toString() {
        return S.fmt("Foo[%s]", id);
    }
}
