package demo.helloworld;

import org.osgl.mvc.annotation.Catch;

public class Interceptor {

    @Catch(XyzExcpetion.class)
    public String handleException(XyzExcpetion e) {
        return "xyz exception happened";
    }

}
