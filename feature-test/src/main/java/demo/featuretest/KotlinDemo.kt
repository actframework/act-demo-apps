package demo.featuretest

import act.controller.Controller
import act.controller.annotation.TemplateContext
import act.controller.annotation.UrlContext
import org.osgl.mvc.annotation.GetAction

@UrlContext("/kotlin")
@TemplateContext("/kotlin")
class KotlinDemo : Controller.Util() {

    @GetAction
    fun home() {
    }

    @GetAction("hello")
    fun hello() {
        text("Hello from Kotlin")
    }

}

fun main(args: Array<String>) {
    act.Act.start("Kotlin Demo")
}