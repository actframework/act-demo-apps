package demo.injection;

import act.inject.AutoBind;

@AutoBind
public interface GreetingService {
    String greeting();
}
