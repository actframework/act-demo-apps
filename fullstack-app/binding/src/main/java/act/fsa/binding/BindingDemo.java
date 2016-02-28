package act.fsa.binding;

import act.boot.app.RunApp;
import org.osgl.mvc.annotation.GetAction;

public class BindingDemo {

    @GetAction
    public void home() {
    }

    public static void main(String[] args) throws Exception {
        System.setProperty("idgen.node_id.effective_ip_bytes.int", "1");
        RunApp.start(BindingDemo.class);
    }

}
