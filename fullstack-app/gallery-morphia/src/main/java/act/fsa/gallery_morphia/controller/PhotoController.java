package act.fsa.gallery_morphia.controller;

import act.controller.Controller;
import act.fsa.gallery_morphia.model.Photo;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.result.Result;

@Controller("/photo")
public class PhotoController extends Controller.Util {

    @GetAction("/add")
    public void addPhotoForm() {
    }
}
