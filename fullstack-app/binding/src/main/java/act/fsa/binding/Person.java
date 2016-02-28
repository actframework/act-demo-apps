package act.fsa.binding;

import act.data.Data;
import org.osgl.$;
import org.osgl.util.S;

@Data
public class Person {

    public static final String TABLE_VIEW = "id,fullName,address.asString as address";

    private String id;
    private String firstName;
    private String lastName;
    private Address address;

    protected Person() {}

    public Person(String id, String firstName, String lastName, Address address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return S.builder(firstName).append(" ").append(lastName).toString();
    }

    public Address getAddress() {
        return address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return S.fmt("%s %s", firstName, lastName);
    }

    public static Person random(String id) {
        String firstName = $.random("Tom", "Peter", "Joe", "Kate", "Leonard", "Alex", "Owen");
        String lastName = $.random("Cincotta", "Luo", "Wall", "Berry", "Chris", "Lagan");
        Address address = Address.random();
        return new Person(id, firstName, lastName, address);
    }

}
