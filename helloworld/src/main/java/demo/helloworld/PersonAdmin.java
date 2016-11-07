package demo.helloworld;

import act.controller.Controller;
import act.event.EventBus;
import org.osgl.aaa.NoAuthentication;
import org.osgl.mvc.annotation.PostAction;

import javax.inject.Inject;

@NoAuthentication
@Controller(port = "admin", value = "/admin")
public class PersonAdmin extends Controller.Util {

    @Inject
    private Person.Dao dao;

    @PostAction("prsn")
    public Person createPerson(Person person, EventBus eventBus) {
        dao.save(person);
        eventBus.trigger("user-signup", person);
        return person;
    }

}
