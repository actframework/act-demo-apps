package act.fsa.gallery_morphia.controller;

import act.controller.Controller;
import org.osgl.mvc.annotation.GetAction;

@Controller("/photo")
public class PhotoController extends Controller.Util {

    @GetAction("/add")
    public void addPhotoForm() {
    }
}
