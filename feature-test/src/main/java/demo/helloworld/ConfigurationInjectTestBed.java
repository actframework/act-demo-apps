package demo.helloworld;

import act.handler.UnknownHttpMethodProcessor;
import org.osgl.inject.annotation.Configuration;
import org.osgl.mvc.annotation.GetAction;

public class ConfigurationInjectTestBed {

    @Configuration("handler.unknown_http_method.impl")
    UnknownHttpMethodProcessor p1;

    @Configuration("act.handler.unknown_http_method.impl")
    UnknownHttpMethodProcessor p2;

    @Configuration("handler.unknown_http_method")
    UnknownHttpMethodProcessor p3;

    @GetAction("/inject/conf")
    public String check() {
        if (null == p1) {
            return "p1 should not be null";
        }
        if (p1 != p2 || p2 != p3) {
            return "p1 p2 and p3 shall be the same instance";
        }
        return "success";
    }
}
