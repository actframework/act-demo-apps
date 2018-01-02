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

import act.storage.Store;
import org.mongodb.morphia.annotations.Entity;
import org.osgl.storage.ISObject;

@Store("store2:")
@Entity("simg2")
public class SingleImageExt extends SingleImage {

    private SingleImageExt() {}

    public SingleImageExt(String title, ISObject image) {
        super(title, image);
    }

}
