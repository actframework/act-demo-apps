package demo.excel;

/*-
 * #%L
 * actframework app demo - excel
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

import act.app.App;
import org.osgl.$;
import org.osgl.inject.BeanSpec;
import org.osgl.inject.Genie;
import org.osgl.inject.loader.ElementLoaderBase;
import org.osgl.util.C;
import org.osgl.util.S;

import java.util.*;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TestDataGenerator extends ElementLoaderBase<Employee> {

    private List<Employee> testData;

    @Inject
    public TestDataGenerator(App app) {
        generateTestData(app);
    }

    @Override
    public Iterable<Employee> load(Map<String, Object> map, BeanSpec beanSpec, Genie genie) {
        return testData;
    }

    @Override
    public $.Function<Employee, Boolean> filter(Map<String, Object> map, BeanSpec beanSpec) {
        return $.F.yes();
    }

    private void generateTestData(App app) {
        testData = new ArrayList<>();
        Employee.Grade[] grades = Employee.Grade.values();
        List<String> nameList = new ArrayList<>();
        nameList.addAll(names);
        Collections.shuffle(nameList);
        for (String name : nameList) {
            testData.add(generateEmployee(app.cuid(), name, $.random(grades)));
        }
    }

    private Employee generateEmployee(String id, String name, Employee.Grade grade) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFullName(name);
        employee.setGrade(grade);
        return employee;
    }


    private static List<String> names = C.listOf(
            ("Jess Branch  \n" +
                    "Ashlie Lineberry  \n" +
                    "Greta Barrette  \n" +
                    "Claire Spurrier  \n" +
                    "Viva Brough  \n" +
                    "Joannie Redwine  \n" +
                    "Venus Farina  \n" +
                    "Janett Gwin  \n" +
                    "Claudine Uselton  \n" +
                    "Gaylene Melendy  \n" +
                    "Joe Pauls  \n" +
                    "Nickolas Pille  \n" +
                    "Adalberto Lashbrook  \n" +
                    "Francina Tickle  \n" +
                    "Jada Worden  \n" +
                    "Keven Hommel  \n" +
                    "Luetta Winkler  \n" +
                    "Vernetta Bogard  \n" +
                    "Ashli Thiel  \n" +
                    "Marcellus Salone  \n" +
                    "Colby Marts  \n" +
                    "Shanti Denney  \n" +
                    "Anissa Schiro  \n" +
                    "Marya Samuels  \n" +
                    "Arletta Mckechnie  \n" +
                    "Tarsha Kull  \n" +
                    "Darron Stoneman  \n" +
                    "Paulina Honahni  \n" +
                    "Jeannette Abee  \n" +
                    "Ninfa Wann  \n" +
                    "Alise Breton  \n" +
                    "Siobhan Demark  \n" +
                    "Kelsie Messineo  \n" +
                    "Kaye Esterline  \n" +
                    "Fallon Battaglia  \n" +
                    "Marisela Bramblett  \n" +
                    "Zoe Crusoe  \n" +
                    "Yung Gordy  \n" +
                    "Brianna Vanfossen  \n" +
                    "Chau Ridinger  \n" +
                    "Roscoe Nobile  \n" +
                    "Melody Lamothe  \n" +
                    "Nada Eis  \n" +
                    "Sherley Whitenack  \n" +
                    "Jasmin Nordyke  \n" +
                    "Phuong Nies  \n" +
                    "Erlinda Joachim  \n" +
                    "Ruthie Soules  \n" +
                    "Homer Baptiste  \n" +
                    "Jovita Lahman").split("[\n]")).map(S.F.TRIM);
}
