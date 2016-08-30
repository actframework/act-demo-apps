package act.fsa.helloworld;

import act.app.conf.AppConfigurator;
import act.security.CSRFProtector;

public class MyConfig extends AppConfigurator {
    @Override
    public void configure() {
        this.sessionEncrypt(true);
        this.i18n(true);
        this.csrf().disable().protector(CSRFProtector.Predefined.RANDOM);
    }
}
