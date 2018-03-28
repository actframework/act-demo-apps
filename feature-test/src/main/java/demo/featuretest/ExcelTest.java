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

import act.app.ActionContext;
import act.controller.annotation.TemplateContext;
import act.data.annotation.Data;
import act.util.SimpleBean;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.result.Result;
import org.osgl.util.S;

import static act.controller.Controller.Util.render;

@SuppressWarnings("unused")
public class ExcelTest {

    @Data
    public static class User implements SimpleBean {
        public String name;

        public User(String name) {
            this.name = name;
        }
    }

    @GetAction("/excel/test")
    @TemplateContext("report")
    public Result testExcel(ActionContext context) {
        User user = new User(S.random());
        context.param("filename", user.name + ".xls");
        return render("user", user);
    }

}
