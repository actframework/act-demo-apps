package demo.excel;

import act.app.App;
import org.osgl.$;
import org.osgl.Osgl;
import org.osgl.inject.BeanSpec;
import org.osgl.inject.Genie;
import org.osgl.inject.loader.ElementLoaderBase;
import org.osgl.util.C;
import org.osgl.util.S;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public Osgl.Function<Employee, Boolean> filter(Map<String, Object> map, BeanSpec beanSpec) {
        return $.F.yes();
    }

    private void generateTestData(App app) {
        testData = new ArrayList<Employee>();
        Employee.Grade[] grades = Employee.Grade.values();
        for (String name : names) {
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
