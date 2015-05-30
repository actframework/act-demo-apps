package act.fsa.guice;

import act.fsa.guice.impl.HomeServiceImpl;
import com.google.inject.ImplementedBy;
import org.osgl.mvc.result.Result;

@ImplementedBy(HomeServiceImpl.class)
public interface HomeService {
    Result welcome();
}
