package demo.helloworld;

import act.Act;
import act.app.App;
import act.cli.Command;
import act.cli.Optional;
import act.cli.Required;
import act.controller.Controller;
import act.event.EventBus;
import act.job.Every;
import act.job.InvokeAfter;
import act.util.PropertySpec;
import org.osgl.inject.annotation.Provided;
import org.osgl.logging.LogManager;
import org.osgl.logging.Logger;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.annotation.PutAction;
import org.osgl.util.S;

import javax.inject.Inject;

@Controller("/prsn")
public class PersonController extends Controller.Util {

    private static final Logger logger = LogManager.get(PersonController.class);

    @Inject
    private Person.Dao dao;

    @PostAction
    public Person createPerson(Person person, EventBus eventBus) {
        logger.debug("creating a person: %s", person.getFullName());
        dao.save(person);
        eventBus.trigger("user-signup", person);
        return person;
    }

    @Command(name = "user.update", help = "update a user specified by id")
    @PutAction("{id}")
    public Person updatePerson(
            @Required("specify user id") String id,
            @Optional("specify user first name") String firstName,
            @Optional("speicify user last name") String lastName,
            @Optional("specify user age") Integer age
    ) {
        Person prsn = dao.findById(id);
        if (S.notBlank(firstName)) {
            prsn.setFirstName(firstName);
        }
        if (S.notBlank(lastName)) {
            prsn.setLastName(lastName);
        }
        if (null != age) {
            prsn.setAge(age);
        }
        dao.save(prsn);
        return prsn;
    }

    @Command(name = "user.drop", help = "purge all testing data", mode = Act.Mode.DEV)
    public void purgeTestData() {
        dao.drop();
    }

    @GetAction
    @PropertySpec(http = "-age", cli = "-id")
    @Command(name = "user.list", help = "list all users")
    public Iterable<Person> findPerson() {
        return dao.personOrderedByAge();
    }

    @GetAction("{id}")
    public Person getPerson(String id) {
        return dao.findById(id);
    }

    @Command(name = "user.password", help = "update user's password")
    public void updateUserPassword(
            @Required("specify user by email") String email,
            @Required("specify user's new password") String password
    ) {
        Person person = dao.findByEmail(email);
        person.setPassword(password);
        dao.save(person);
    }

}
