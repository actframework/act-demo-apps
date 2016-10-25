package demo.excel;

import act.data.annotation.Data;

@Data
public class Employee {

    enum Grade {
        E06, E07, E08, E09, E10, E11
    }

    private String id;
    private String firstName;
    private String lastName;
    private Grade grade;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public void setFullName(String fullName) {
        String[] sa = fullName.split("[\\s]+");
        this.firstName = sa[0];
        this.lastName = sa[1];
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", grade=" + grade +
                '}';
    }
}
