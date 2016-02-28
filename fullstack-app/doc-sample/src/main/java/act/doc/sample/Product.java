package act.doc.sample;

import act.db.DB;
import org.mongodb.morphia.annotations.Entity;
import act.db.morphia.MorphiaModel;

@Entity("prod")
public class Product extends MorphiaModel<Product> {
    private String name;
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
