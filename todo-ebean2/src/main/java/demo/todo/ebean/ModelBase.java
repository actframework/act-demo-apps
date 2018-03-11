package demo.todo.ebean;

import act.db.CreatedAt;
import act.db.LastModifiedAt;
import org.joda.time.DateTime;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class ModelBase {

    @CreatedAt
    private DateTime created;

    @LastModifiedAt
    private DateTime lastModified;

    public DateTime getCreated() {
        return created;
    }

    public DateTime getLastModified() {
        return lastModified;
    }
}
