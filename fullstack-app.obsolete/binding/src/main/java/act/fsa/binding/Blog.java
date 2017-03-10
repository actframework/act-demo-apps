package act.fsa.binding;

import org.osgl.util.N;
import org.osgl.util.S;

public class Blog {
    private String title;
    private String text;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static Blog random() {
        Blog blog = new Blog();
        blog.setTitle(S.random(N.randInt(18) + 11));
        blog.setText(S.random(N.randInt(100) + 50));
        return blog;
    }
}
