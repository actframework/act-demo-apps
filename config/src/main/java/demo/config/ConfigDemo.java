package demo.config;

import act.Act;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.result.Result;

import static act.controller.Controller.Util.render;
import static act.controller.Controller.Util.text;

/**
 * App entry for Config Demo project
 */
public class ConfigDemo {

    // This is the root URL handler. It will load the template
    // stored in resources/views/demo/config/ConfigDemo.home.html
    // Note the template files are not sit under `rythm` because
    // in the MyAppConfig class we configured the `templateHome`
    /// to be "views"
    @GetAction
    public void home() {
    }

    // This is going to be overwritten by routes.conf
    @GetAction("/bye")
    public void byeSpring() {
        text("bye Spring!!");
    }

    // This will display all application setttings
    @GetAction("/setting")
    public static Result showAppSettings() {
        return render();
    }

    public static void main(String[] args) throws Exception {
        Act.start("Config Demo");
    }

}
