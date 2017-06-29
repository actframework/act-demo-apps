package demo.helloworld.gh251;

import act.util.SimpleBean;
import org.osgl.$;
import org.osgl.util.N;

public class Foo implements SimpleBean {
    public int n;
    public boolean b;

    public Foo() {
        n = N.randInt();
        b = $.random(true, false);
    }
}
