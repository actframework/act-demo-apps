package demo.helloworld;

import act.cli.Command;
import act.controller.annotation.UrlContext;
import demo.helloworld.gh251.Gh251Model;

@UrlContext("251")
public class GH251 extends GHTest {

    @Command(name = "gh251", help = "verify github 251 issue")
    public Iterable test() {
        return Gh251Model.randomList();
    }

}
