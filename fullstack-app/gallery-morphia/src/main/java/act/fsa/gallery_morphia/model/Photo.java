package act.fsa.gallery_morphia.model;

import act.data.Data;
import act.db.morphia.MorphiaDao;
import act.db.morphia.MorphiaModel;
import act.storage.Managed;
import act.storage.StorageServiceManager;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Transient;
import org.osgl.storage.ISObject;
import org.osgl.storage.IStorageService;

@Entity("img")
@Data
public class Photo extends MorphiaModel {

    public static final String VIEW = "title,description,imageUrl";

    @Managed
    private ISObject image;

    private String title;

    @Property("desc")
    private String description;

    public ISObject getImage() {
        return image;
    }

    public void setImage(ISObject image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return null == image ? "" : image.getAttribute(ISObject.ATTR_URL);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // The DAO class corresponding to the Image entity
    public static class Dao extends MorphiaDao<ObjectId, Photo, Dao> {
        public Dao() {
            super(Photo.class);
        }
    }

}
