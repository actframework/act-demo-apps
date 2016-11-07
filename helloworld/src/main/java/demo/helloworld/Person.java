package demo.helloworld;

import act.Act;
import act.db.morphia.MorphiaDao;
import act.db.morphia.MorphiaModel;
import org.apache.commons.codec.Charsets;
import org.mongodb.morphia.annotations.Entity;
import org.osgl.util.S;

import java.util.List;

@Entity("prsn")
public class Person extends MorphiaModel<Person> {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int age;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return S.fmt("%s %s", firstName, lastName);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = Act.crypto().passwordHash(password);
    }

    public void generatePassword() {
        this.password = S.random(5);
    }

    public boolean verifyPassword(char[] password) {
        return Act.crypto().verifyPassword(new String(password), this.password);
    }

    public static class Dao extends MorphiaDao<Person> {

        List<Person> personOrderedByAge() {
            return q().order("-age").asList();
        }

        Person oldest() {
            return q().order("-age").get();
        }

        Person findByEmail(String email) {
            return findOneBy("email", email);
        }

        Person authenticate(String username, String password) {
            Person person = findByEmail(username);
            if (null != person && person.verifyPassword(password.toCharArray())) {
                return person;
            }
            return null;
        }

    }

}
