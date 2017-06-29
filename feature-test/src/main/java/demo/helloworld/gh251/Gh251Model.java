package demo.helloworld.gh251;

import act.data.annotation.Data;
import act.util.SimpleBean;
import org.osgl.$;
import org.osgl.util.C;
import org.osgl.util.S;

import java.util.List;
import java.util.Locale;

@Data
public class Gh251Model implements SimpleBean {

    public String name;
    public Locale locale;
    public Foo[] foos;

    public Gh251Model() {
        this.name = S.random();
        this.locale = Locale.CHINA;
        if ($.random(true, false)) {
            this.foos = C.list(new Foo(), new Foo()).toArray(new Foo[2]);
        }
    }

    public static List<Gh251Model> randomList() {
        return C.list(new Gh251Model(), new Gh251Model());
    }
}
