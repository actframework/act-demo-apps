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

    // This is the root URL handler. It will load the template
    // stored in resources/rythm/demo/upload/UploadDemo/home.html
    @GetAction
    public void home() {
    }

    @GetAction("/single")
    public void singleImageHome() {
        List<SingleImage> images = singleDao.findAllAsList();
        render(images);
    }

    @PostAction("/single/by_file")
    public void uploadSingleWithFile(String title, File image) {
        singleDao.save(new SingleImage(title, SObject.of(image)));
        redirect("/single");
    }

    @PostAction("/single/by_sobj")
    public void uploadSingleWithSObject(String title, ISObject image) {
        singleDao.save(new SingleImage(title, image));
        redirect("/single");
    }

    @PostAction("/single/by_bind")
    public void uploadSingleWithAutoBinding(SingleImage image) {
        singleDao.save(image);
        redirect("/single");
    }

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
