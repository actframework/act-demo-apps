package demo.helloworld.gh193;

import act.controller.annotation.UrlContext;
import act.db.DbBind;
import act.db.morphia.MorphiaAdaptiveRecord;
import act.db.morphia.MorphiaDao;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.osgl.$;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PatchAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.annotation.PutAction;
import org.osgl.util.E;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity("emp")
public class Employee extends MorphiaAdaptiveRecord<Employee> {

    public String firstName;
    public String lastName;
    public Address address;
    public List<ObjectId> idList = new ArrayList<>();

    @UrlContext("/gh193/employees")
    public static class Service extends MorphiaDao<Employee> {

        @GetAction
        public Iterable<Employee> list() {
            return findAll();
        }

        @GetAction("{id}")
        public Employee show(@DbBind("id") @NotNull Employee employee) {
            return employee;
        }

        @PostAction
        public Employee create(Employee employee) {
            return save(employee);
        }

        @PatchAction("{id}")
        public Employee replace(Employee employee, ObjectId id) {
            E.illegalArgumentIf($.ne(employee._id(), id));
            return save(employee);
        }

        @PutAction("{id}")
        public Employee update(@DbBind("id") @NotNull Employee employee, Map<String, Object> updates) {
            employee.putValues(updates);
            return save(employee);
        }

    }


}
