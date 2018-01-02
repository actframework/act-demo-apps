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
