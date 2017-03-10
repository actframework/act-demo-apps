package act.fsa.gallery_morphia;

import act.boot.app.RunApp;
import org.osgl.mvc.annotation.GetAction;

/**
 * The application entry
 */
public class MorphiaGalleryApp {

    @GetAction
    public void home() {}

    public static void main(String[] args) throws Exception {
        RunApp.start("gallery", "2.0", MorphiaGalleryApp.class);
    }
}
