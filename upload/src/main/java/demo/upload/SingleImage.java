package demo.upload;

import act.db.morphia.MorphiaModel;
import act.storage.Store;
import org.mongodb.morphia.annotations.Entity;
import org.osgl.$;
import org.osgl.storage.ISObject;

/**
 * A model with single image attachment
 */
@Store("store1:")
@Entity("simg")
public class SingleImage extends MorphiaModel<SingleImage> {

    private String title;

    @Store
    private ISObject image;

    private SingleImage() {}

    public SingleImage(String title, ISObject image) {
        this.title = $.notNull(title);
        this.image = $.notNull(image);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ISObject getImage() {
        return image;
    }

    public String getImageUrl() {
        return null == image ? null : image.getUrl();
    }

    public void setImage(ISObject image) {
        this.image = image;
    }
}
