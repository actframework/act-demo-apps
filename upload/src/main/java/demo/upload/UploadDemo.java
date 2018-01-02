package demo.upload;

/*-
 * #%L
 * actframework app demo - upload
 * %%
 * Copyright (C) 2018 ActFramework
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import act.Act;
import act.data.SObjectResolver;
import act.db.DbBind;
import act.db.morphia.MorphiaDao;
import act.storage.StorageServiceManager;
import act.validation.NotBlank;
import org.osgl.mvc.annotation.DeleteAction;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.storage.ISObject;
import org.osgl.storage.IStorageService;
import org.osgl.storage.impl.SObject;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
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

    @GetAction("/rmt")
    public String testRemoteFetch(StorageServiceManager ssm) {
        String url = "https://propertymanage.atlassian.net/secure/projectavatar?pid=10400&avatarId=11200";
        ISObject sobj = new SObjectResolver().resolve(url);
        //sobj.setAttribute(ISObject.ATTR_FILE_NAME, "remote_file.png");
        IStorageService ss = ssm.storageService("store1");
        sobj = ss.put(ss.getKey(), sobj);
        return sobj.getUrl();
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
    public void uploadSingleWithFile(@NotBlank String title, @NotNull File image) {
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

    @DeleteAction("/single/ext/{id}")
    public void deleteSingleExtImage(@DbBind("id") SingleImageExt image) {
        notFoundIfNull(image);
        singleExtDao.delete(image);
        redirect("/single/ext");
    }

    public static void main(String[] args) throws Exception {
        Act.start();
    }

}
