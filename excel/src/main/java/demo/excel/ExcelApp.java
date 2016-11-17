package demo.excel;

import act.Version;
import act.app.ActionContext;
import act.boot.app.RunApp;
import org.osgl.inject.annotation.LoadCollection;
import org.osgl.mvc.annotation.GetAction;

import java.util.List;

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
