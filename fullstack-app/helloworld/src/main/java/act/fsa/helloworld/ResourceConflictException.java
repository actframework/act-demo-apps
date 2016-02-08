package act.fsa.helloworld;

import org.osgl.mvc.annotation.ResponseStatus;

@ResponseStatus(409)
public class ResourceConflictException extends Exception {
}
