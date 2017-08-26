package demo.helloworld;

import org.osgl.exception.UnexpectedIOException;
import org.osgl.mvc.annotation.Catch;
import org.osgl.mvc.result.Result;

import static act.controller.Controller.Util.renderText;

public class ExceptionAdvice {

    @Catch(IllegalAccessException.class)
    public Result handleAnotherException(IllegalAccessException e) {
        return renderText(e.getClass().getName());
    }

    @Catch(UnexpectedIOException.class)
    public Result handleException(UnexpectedIOException e) {
        return renderText(e.getClass().getName());
    }

}
