package demo.todo.hibernate;

import act.db.CreatedAt;
import act.db.LastModifiedAt;

import java.util.Date;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class ModelBase {

    @CreatedAt
    private Date created;

    @LastModifiedAt
    private Date lastModified;

    public Date getCreated() {
        return created;
    }

    public Date getLastModified() {
        return lastModified;
    }
}
