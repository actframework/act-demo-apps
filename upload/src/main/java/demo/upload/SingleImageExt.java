package demo.upload;

import act.db.morphia.MorphiaDao;
import act.storage.Store;
import org.mongodb.morphia.annotations.Entity;
import org.osgl.storage.ISObject;

@Store("store2:")
@Entity("simg2")
public class SingleImageExt extends SingleImage {

    private SingleImageExt() {}

    public SingleImageExt(String title, ISObject image) {
        super(title, image);
    }

}
