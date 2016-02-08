package act.fsa.gallery_morphia.controller;

import act.controller.Controller;
import act.fsa.gallery_morphia.model.Photo;
import act.util.PropertySpec;
import org.osgl.$;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.storage.impl.SObject;

import javax.inject.Inject;
import java.io.File;

/**
 * Provides Restful service
 */
@Controller("/api/v1/photo")
public class PhotoApi {

    private Photo.Dao photoDao;

    @Inject
    public PhotoApi(Photo.Dao dao) {
        this.photoDao = dao;
    }

    @GetAction
    @PropertySpec(Photo.VIEW)
    public Iterable<Photo> list() {
        return photoDao.findAll();
    }

    @PostAction
    public void add(String title, String description, SObject image) {
        Photo photo = new Photo();
        photo.setTitle(title);
        photo.setDescription(description);
        photo.setImage(image);
        photoDao.save(photo);
    }

}
