package act.fsa.helloworld;

import org.osgl.util.S;

public class Person {
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return S.fmt("%s %s", firstName, lastName);
    }

    public static Person random() {
        return new Person(S.random(), S.random());
    }
}
