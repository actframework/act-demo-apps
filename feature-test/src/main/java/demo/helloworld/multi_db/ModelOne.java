package demo.helloworld.multi_db;

import act.controller.annotation.UrlContext;
import act.db.DB;
import act.db.morphia.MorphiaAdaptiveRecord;
import act.db.morphia.MorphiaDao;
import org.mongodb.morphia.annotations.Entity;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.util.S;

@DB("db1")
@Entity("m1")
public class ModelOne extends MorphiaAdaptiveRecord<ModelOne> {
    public String name;
    public ModelOne() {
        this.name = S.random();
    }

    @UrlContext("/mdb/m1")
    public static class Dao extends MorphiaDao<ModelOne> {

        @PostAction
        public ModelOne create() {
            return save(new ModelOne());
        }

    }
}
