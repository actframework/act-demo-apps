package demo.upload;

import act.Version;
import act.app.ActionContext;
import act.boot.app.RunApp;
import act.db.DbBind;
import act.db.morphia.MorphiaDao;
import org.osgl.http.H;
import org.osgl.mvc.annotation.Before;
import org.osgl.mvc.annotation.DeleteAction;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.result.Result;
import org.osgl.storage.ISObject;
import org.osgl.storage.impl.SObject;

import javax.inject.Inject;
import java.io.File;
import java.util.List;

import static act.controller.Controller.Util.*;

/**
 * App entry for Upload Demo project
 */
@SuppressWarnings("unused")
public class UploadDemo {

    @Inject
    private MorphiaDao<SingleImage> singleDao;

    @Inject
    private MorphiaDao<SingleImageExt> singleExtDao;

    @Inject
    private MorphiaDao<MultipleImage> multiDao;

    // This is the root URL handler. It will load the template
    // stored in resources/rythm/demo/upload/UploadDemo/home.html
    @GetAction
    public void home() {
    }

    @GetAction("/multi")
    public void multipleImageHome() {
        List<MultipleImage> images = multiDao.findAllAsList();
        render(images);
    }


    // This action handler demonstrates how to take java.util.File
    // as parameter to handle file upload.
    @PostAction("/multi/by_file")
    public void uploadMultiWithFile(String title, List<File> image) {
        multiDao.save(MultipleImage.ofFiles(title, image));
        redirect("/multi");
    }

    // This action handler demonstrates how to take org.osgl.storage.ISObject
    // as parameter to handle file upload.
    @PostAction("/multi/by_sobj")
    public void uploadMultiWithSObject(String title, List<ISObject> image) {
        multiDao.save(MultipleImage.ofSObjects(title, image));
        redirect("/multi");
    }

    // This action handler demonstrates how to use POJO binding
    // to process file upload
    @PostAction("/multi/by_bind")
    public void uploadMultiWithAutoBinding(MultipleImage image) {
        image.dao().save(image);
        redirect("/multi");
    }


    // This is the method to load testing page for SingleImage operation
    // It will read all SingleImage instances from database and render
    // into the template located at
    // resources/rythm/demo/upload/UploadDemo/singleImageHomt.html
    @GetAction("/single")
    public void singleImageHome() {
        List<SingleImage> images = singleDao.findAllAsList();
        render(images);
    }

    @GetAction("/single/ext")
    public void singleExtHome() {
        List<SingleImageExt> images = singleExtDao.findAllAsList();
        render("singleImageHome", images);
    }

    // This action handler demonstrates how to take java.util.File
    // as parameter to handle file upload.
    @PostAction("/single/by_file")
    public void uploadSingleWithFile(String title, File image) {
        singleDao.save(new SingleImage(title, SObject.of(image)));
        redirect("/single");
    }

    // This action handler demonstrates how to take org.osgl.storage.ISObject
    // as parameter to handle file upload.
    @PostAction("/single/by_sobj")
    public void uploadSingleWithSObject(String title, ISObject image) {
        singleDao.save(new SingleImage(title, image));
        redirect("/single");
    }

    // This action handler demonstrates how to use POJO binding
    // to process file upload
    @PostAction("/single/by_bind")
    public void uploadSingleWithAutoBinding(SingleImageExt image) {
        image.dao().save(image);
        redirect("/single/ext");
    }

    // This action handler demonstrates how to delete a POJO entity
    // and it will automatically delete associated blob file
    @DeleteAction("/single/{id}")
    public void deleteSingleImage(@DbBind("id") SingleImage image) {
        notFoundIfNull(image);
        singleDao.delete(image);
        redirect("/single");
    }

    public static void main(String[] args) throws Exception {
        RunApp.start("Upload Demo", Version.appVersion(), UploadDemo.class);
    }

}
