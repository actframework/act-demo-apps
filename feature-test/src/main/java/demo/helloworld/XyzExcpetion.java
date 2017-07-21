package demo.helloworld;

import org.osgl.exception.UnexpectedException;

public class XyzExcpetion extends UnexpectedException {
    public XyzExcpetion() {
    }

    public XyzExcpetion(String message) {
        super(message);
    }

    public XyzExcpetion(String message, Object... args) {
        super(message, args);
    }

    public XyzExcpetion(Throwable cause) {
        super(cause);
    }

    public XyzExcpetion(Throwable cause, String message, Object... args) {
        super(cause, message, args);
    }
}
