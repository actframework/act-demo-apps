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

/**
 * A model with single image attachment
 */
@Store("store1:")
@Entity("simg")
public class SingleImage extends MorphiaModel<SingleImage> {

    private String title;

    @Store
    private ISObject image;

    protected SingleImage() {}

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
