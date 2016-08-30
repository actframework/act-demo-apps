package act.fsa.helloworld;

import act.app.ActionContext;
import act.controller.Controller;
import act.i18n.TimeZoneResolver;
import org.osgl.http.H;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.result.Result;

import java.util.Locale;

/**
 * Demo ActFramework i18n facilities
 */
@Controller("/i18n")
public class I18n extends Controller.Util {

    @GetAction("timezone")
    public Result timezone(H.Session session) {
        String timezone = session.get(TimeZoneResolver.SESSION_KEY);
        return render(timezone);
    }

    @GetAction("accept")
    public Locale acceptLocale(H.Request request) {
        return request.locale();
    }

    @GetAction("locale")
    public Result locale(ActionContext context) {
        Locale locale = context.locale();
        return render(locale);
    }
}
