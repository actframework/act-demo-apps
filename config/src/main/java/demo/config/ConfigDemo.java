package demo.config;

import act.Version;
import act.app.ActionContext;
import act.boot.app.RunApp;
import org.osgl.http.H;
import org.osgl.mvc.annotation.Before;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.result.Result;

import static act.controller.Controller.Util.*;

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
        RunApp.start("Config Demo", Version.appVersion(), ConfigDemo.class);
    }

}
