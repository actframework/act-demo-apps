package act.fsa.gallery_morphia.model;

import act.data.Data;
import act.db.morphia.MorphiaDao;
import act.db.morphia.MorphiaModel;
import act.storage.StorageServiceManager;
import act.storage.Store;
import org.imgscalr.Scalr;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;
import org.osgl.storage.ISObject;
import org.osgl.storage.impl.SObject;
import org.osgl.util.E;

import javax.imageio.ImageIO;
import javax.inject.Singleton;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Entity("photo")
@Data
public class Photo extends MorphiaModel<Photo> {

    public static final String VIEW = "title,description,imageUrl,thumbnailUrl";
    public static final int THUMBNAIL_WIDTH = 200;

    @Store("img")
    private ISObject image;

    @Store("thumbnail")
    private ISObject thumbnail;

    private String title;

    @Property("desc")
    private String description;

    public ISObject getImage() {
        return image;
    }

    public void setImage(ISObject image) {
        this.image = image;
        genThumbnail();
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

    public String getThumbnailUrl() {
        return null == thumbnail ? "" : thumbnail.getAttribute(ISObject.ATTR_URL);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private void genThumbnail() {
        if (null == image) {
            return;
        }
        try {
            BufferedImage img = ImageIO.read(image.asInputStream());
            BufferedImage imgThumb = Scalr.resize(img, Scalr.Mode.AUTOMATIC, THUMBNAIL_WIDTH);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            saveAwtImage(imgThumb, baos);
            thumbnail = SObject.of(baos.toByteArray());
            thumbnail.setAttributes(image.getAttributes());
        } catch (IOException e) {
            throw E.ioException(e);
        }
    }

    private static void saveAwtImage(BufferedImage image, OutputStream os) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++)
                after.setRGB(x, y, image.getRGB(x, y));
        try {
            ImageIO.write(after, "jpg", os);
        } catch (IOException e) {
            throw E.ioException(e);
        }
    }

    // The DAO class corresponding to the Image entity
    @Singleton
    public static class Dao extends MorphiaDao<Photo> {
        public Dao() {
            super(Photo.class);
        }

        public void delete(Photo photo) {
            super.delete(photo);
            deleteSObject(photo.image);
            deleteSObject(photo.thumbnail);
        }

        private void deleteSObject(ISObject sobj) {
            if (null != sobj) {
                StorageServiceManager.instance().delete(sobj);
            }
        }
    }

}
