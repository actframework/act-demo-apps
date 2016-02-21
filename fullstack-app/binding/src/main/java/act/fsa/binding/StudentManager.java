package act.fsa.binding;

import act.app.App;
import org.osgl.util.C;
import org.osgl.util.Codec;
import org.osgl.util.S;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class StudentManager {

    private App app;

    @Inject
    public StudentManager(App app) {
        this.app = app;
        populateInitData();
    }

    private C.Map<String, Student> data = C.newMap();

    public Student findById(String id) {
        return data.get(id);
    }

    public List<Student> findAll() {
        return C.list(data.values());
    }

    private void populateInitData() {
        for (int i = 0; i < 5; ++i) {
            String id = Codec.encodeBase64(app.cuid());
            if (id.contains("=")) {
                id = S.before(id, "=");
            }
            data.put(id, Student.random(id));
        }
    }


}
