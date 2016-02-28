package act.doc.sample;

import act.app.ActionContext;
import act.controller.Controller;
import act.db.morphia.MorphiaDao;
import org.bson.codecs.PatternCodec;
import org.bson.types.ObjectId;
import org.osgl.http.H;
import org.osgl.mvc.annotation.*;
import org.osgl.util.S;

import javax.inject.Inject;
import java.util.regex.Pattern;


@Controller("/prod")
public class ProductController extends Controller.Util {

    @Inject
    private MorphiaDao<Product> dao;

    @GetAction
    public Iterable<Product> list(String q) {
        if (S.notBlank(q)) {
            return dao.findBy("name", Pattern.compile(q, Pattern.CASE_INSENSITIVE));
        }
        return dao.findAll();
    }

    @GetAction("/{id}")
    public Product show(String id) {
        return dao.findById(new ObjectId(id));
    }

    @PostAction
    public void create(Product product) {
        dao.save(product);
    }

    @PutAction("/{id}/name")
    public void update(String id, String name) {
        Product product = dao.findById(new ObjectId(id));
        notFoundIfNull(product);
        product.setName(name);
        dao.save(product);
    }

    @DeleteAction
    public void delete(String id) {
        dao.deleteById(new ObjectId(id));
    }

    @GetAction("/byName")
    public Iterable<Product> findByName(String name) {
        return dao.findBy("name", name);
    }

    @GetAction("/byMaxPrice")
    public Iterable<Product> findByMaxPrice(int max) {
        return dao.findBy("price <", max);
    }

}