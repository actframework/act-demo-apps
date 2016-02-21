package act.fsa.binding;

import act.data.Data;
import org.osgl.$;
import org.osgl.util.N;
import org.osgl.util.S;

@Data
public class Address {
    private String street;
    private String suburb;
    private String state;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAsString() {
        return toString();
    }

    @Override
    public String toString() {
        return S.builder(street).append(" ").append(suburb).append(" ").append(state).toString();
    }

    public static Address random() {
        Address addr = new Address();
        addr.state = $.random("NSW", "VIC", "QLD", "WA");
        addr.street = S.random(N.randInt(14) + 6);
        addr.suburb = $.random("Cherrybrook", "Sutherland", "Kogarah", "Chatswood", "Burwood", "Liverpool");
        return addr;
    }
}
