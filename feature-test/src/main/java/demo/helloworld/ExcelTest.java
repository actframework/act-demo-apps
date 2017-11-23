package demo.helloworld;

import static act.controller.Controller.Util.render;

import act.app.ActionContext;
import act.controller.annotation.TemplateContext;
import act.data.annotation.Data;
import act.util.SimpleBean;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.result.Result;
import org.osgl.util.S;

@SuppressWarnings("unused")
public class ExcelTest {

    @Data
    public static class User implements SimpleBean {
        public String name;

        public User(String name) {
            this.name = name;
        }
    }

    @GetAction("/excel/test")
    @TemplateContext("report")
    public Result testExcel(ActionContext context) {
        User user = new User(S.random());
        context.param("filename", user.name + ".xls");
        return render("user", user);
    }

}
