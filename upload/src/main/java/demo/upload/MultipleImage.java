package demo.upload;

import act.db.morphia.MorphiaModel;
import act.storage.Store;
import org.mongodb.morphia.annotations.Entity;
import org.osgl.$;
import org.osgl.storage.ISObject;
import org.osgl.storage.impl.SObject;
import org.osgl.util.C;

import java.io.File;
import java.util.List;

/**
 * A model with multiple image attachment
 */
@Store("store1:m")
@Entity("mimg")
public class MultipleImage extends MorphiaModel<MultipleImage> {

    private String title;

    @Store
    private List<ISObject> images;

    protected MultipleImage() {}

    public MultipleImage(String title, List<ISObject> images) {
        this.title = title;
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ISObject> getImages() {
        return images;
    }

    public void setImages(List<ISObject> images) {
        this.images = images;
    }

    public List<String> getImageUrls() {
        return C.list(images).filter($.F.notNull()).map(ISObject::getUrl);
    }

    public static MultipleImage ofSObjects(String title, List<ISObject> images) {
        return new MultipleImage(title, images);
    }

    public static MultipleImage ofFiles(String title, List<File> files) {
        return new MultipleImage(title, C.list(files).map(SObject::of));
    }
}
