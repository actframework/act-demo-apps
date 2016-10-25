package demo.excel;

import act.Version;
import act.app.ActionContext;
import act.app.event.AppEventId;
import act.boot.app.RunApp;
import act.event.On;
import act.job.OnAppEvent;
import org.osgl.http.H;
import org.osgl.inject.annotation.LoadCollection;
import org.osgl.logging.LogManager;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.result.Result;

import java.util.List;

import static act.controller.Controller.Util.render;

/**
 * A sample application demonstrate how to generate Excel/csv report in Act
 */
@SuppressWarnings("unused")
public class ExcelApp {

    @LoadCollection(TestDataGenerator.class)
    private List<Employee> employees;

    @GetAction
    public List<Employee> home(ActionContext context) {
        return employees;
    }

    public static void main(String[] args) throws Exception {
        RunApp.start("Excel Demo", Version.appVersion(), ExcelApp.class);
    }

}
