package act.doc.sample;

import act.controller.Controller;
import act.db.ebean.EbeanDao;
import act.event.ActEvent;
import act.event.EventBus;
import org.osgl.mvc.annotation.DeleteAction;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.annotation.PutAction;

import javax.inject.Inject;

@Controller("/ctct")
public class ContactController extends Controller.Util {

    private EbeanDao<Long, Contact> dao;

    private EventBus eventBus;

    @Inject
    public ContactController(EbeanDao<Long, Contact> dao, EventBus eventBus) {
        this.dao = dao;
        this.eventBus = eventBus;
    }

    @GetAction
    public Iterable<Contact> list() {
        return dao.findAll();
    }

    @PostAction
    public void create(Contact ctct) {
        dao.save(ctct);
        eventBus.trigger("new-contact", ctct);
        eventBus.trigger(new ActEvent<Contact>(ctct));
        System.out.println("event emitted");
    }

    @GetAction("{id}")
    public Contact show(long id) {
        return dao.findById(id);
    }

    @PutAction("{id}/addr")
    public void updateAddress(long id, String value) {
        Contact ctct = dao.findById(id);
        notFoundIfNull(ctct);
        ctct.setAddress(value);
        dao.save(ctct);
    }

    @DeleteAction
    public void delete(long id) {
        dao.deleteById(id);
    }

}
