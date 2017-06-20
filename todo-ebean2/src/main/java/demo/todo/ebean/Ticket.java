package demo.todo.ebean;

import act.controller.annotation.UrlContext;
import act.db.ebean2.EbeanDao;
import act.util.SimpleBean;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Entity(name = "ticket")
public class Ticket implements SimpleBean {

    @Id
    public Long id;

    public String name;

    public Date date;

    @UrlContext("/ticket")
    public static class Dao extends EbeanDao<Long, Ticket> {

        @GetAction
        public Iterable<Ticket> list() {
            return findAll();
        }

        @PostAction
        public Ticket create(Ticket ticket) {
            return save(ticket);
        }

    }

}
