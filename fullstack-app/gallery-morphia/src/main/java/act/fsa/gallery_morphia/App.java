package act.fsa.gallery_morphia;

import act.boot.app.RunApp;
import org.osgl.mvc.annotation.GetAction;

/**
 * The application entry
 */
public class App {

    @GetAction
    public void home() {}

    public static void main(String[] args) throws Exception {
        RunApp.start("gallery", App.class);
    }
}
