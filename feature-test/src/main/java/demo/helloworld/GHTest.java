package demo.helloworld;

import act.controller.Controller;
import act.controller.annotation.TemplateContext;
import act.controller.annotation.UrlContext;

@UrlContext("/gh")
@TemplateContext("/gh")
public class GHTest extends Controller.Util {
}
