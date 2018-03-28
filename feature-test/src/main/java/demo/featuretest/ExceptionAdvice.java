package demo.featuretest;

/*-
 * #%L
 * actframework Feature Test App
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

import org.osgl.exception.UnexpectedIOException;
import org.osgl.mvc.annotation.Catch;
import org.osgl.mvc.result.Result;

import static act.controller.Controller.Util.renderText;

public class ExceptionAdvice {

    @Catch(IllegalAccessException.class)
    public Result handleAnotherException(IllegalAccessException e) {
        return renderText(e.getClass().getName());
    }

    @Catch(UnexpectedIOException.class)
    public Result handleException(UnexpectedIOException e) {
        return renderText(e.getClass().getName());
    }

}
