package demo.helloworld.multi_db;

import act.controller.annotation.UrlContext;
import act.db.DB;
import act.db.morphia.MorphiaAdaptiveRecord;
import act.db.morphia.MorphiaDao;
import org.mongodb.morphia.annotations.Entity;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.util.N;

@DB("db2")
@Entity("m2")
public class ModelTwo extends MorphiaAdaptiveRecord<ModelTwo> {
    public int id;

    public ModelTwo() {
        this.id = N.randInt();
    }

    @UrlContext("/mdb/m2")
    public static class Dao extends MorphiaDao<ModelTwo> {

        @PostAction
        public ModelTwo create() {
            return save(new ModelTwo());
        }

    }

}
