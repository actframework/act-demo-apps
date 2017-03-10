package act.fsa.binding;

import org.osgl.util.C;
import org.osgl.util.N;

import java.util.Map;
import java.util.List;

public class Student extends Person {

    private Map<Subject, Integer> scores = C.newMap();
    private List<Blog> blogList = C.newList();

    private Student() {}

    public Student(Person person) {
        super(person.getId(), person.getFirstName(), person.getLastName(), person.getAddress());
    }

    public static Student random(String id) {
        Student student = new Student(Person.random(id));
        student.scores = randomScores();
        student.blogList = randomBlogs();
        return student;
    }

    public Map<Subject, Integer> getScores() {
        return C.map(scores);
    }

    public void setScores(Map<Subject, Integer> scores) {
        this.scores = C.newMap(scores);
    }

    public List<Blog> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<Blog> blogList) {
        this.blogList = blogList;
    }

    private static Map<Subject, Integer> randomScores() {
        Map<Subject, Integer> scores = C.newMap();
        scores.put(Subject.ENGLISH, 60 + N.randInt(40));
        scores.put(Subject.MATH, 60 + N.randInt(40));
        scores.put(Subject.SCIENCE, 60 + N.randInt(40));
        return scores;
    }

    private static List<Blog> randomBlogs() {
        List<Blog> list = C.newList();
        int max = N.randInt(10) + 1;
        for (int i = 0; i < max; ++i) {
            list.add(Blog.random());
        }
        return list;
    }
}
