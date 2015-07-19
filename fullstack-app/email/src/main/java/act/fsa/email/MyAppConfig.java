package act.fsa.email;

import act.app.conf.AppConfigurator;

public class MyAppConfig extends AppConfigurator<MyAppConfig> {
    @Override
    public void configure() {
        prop("mailer", "notification");
        prop("mailer.notification.to", "user1@xxx.com, user2@xxx.com");
    }
}
