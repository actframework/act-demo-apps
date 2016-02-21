package act.fsa.binding;

import act.app.ActionContext;
import act.cli.Command;
import act.cli.JsonView;
import act.cli.TableView;
import act.controller.Controller;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;

import javax.inject.Inject;
import java.util.List;

@Controller("/api/student")
public class StudentApi extends Controller.Util {

    private StudentManager studentManager;
    private ActionContext context;

    @Inject
    public StudentApi(StudentManager studentManager) {
        this.studentManager = studentManager;
    }

    @Command(value = "st.list", help = "list all students")
    @GetAction
    @TableView
    //@PropertySpec(http = "-x", cli = Student.TABLE_VIEW)
    public List<Student> list() {
        return studentManager.findAll();
    }

    @Command(name = "st.show", help = "show information about a student")
    @GetAction("/{id}")
    @JsonView
    public Student show(String id) {
        Student student = studentManager.findById(id);
        notFoundIfNull(student);
        return student;
    }

    @PostAction("/{id}")
    public void update(String id, Student student) {
        System.out.println(student);
    }

}
