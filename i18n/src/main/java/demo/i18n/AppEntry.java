package demo.i18n;

import act.Act;
import act.controller.Controller;
import act.inject.DefaultValue;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.osgl.mvc.annotation.GetAction;

import java.util.Date;

/**
 * A simple hello world app entry
 *
 * Run this app, try to update some of the code, then
 * press F5 in the browser to watch the immediate change
 * in the browser!
 */
@SuppressWarnings("unused")
public class AppEntry extends Controller.Util {

    /**
     * The home (`/`) endpoint.
     *
     * This will accept a query parameter named `who` and
     * render a template (resources/rythm/__package__/AppEntry/home.html),
     * where `__package__` corresponding to the package name, e.g.
     * if your package is `com.mycomp.myproj`, then `__package__`
     * is `com/mycomp/myproj`.
     *
     * @param who
     *      request query parameter to specify the hello target.
     *      default value is `World`.
     */
    @GetAction
    public void home(@DefaultValue("World") String who) {
        Date javaDate = new Date();
        DateTime dateTime = DateTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        render(who, javaDate, dateTime, localDateTime, localDate, localTime);
    }

    public static void main(String[] args) throws Exception {
        Act.start();
    }

}
