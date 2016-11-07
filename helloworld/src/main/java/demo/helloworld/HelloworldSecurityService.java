package demo.helloworld;

import act.aaa.ActAAAService;

public class HelloworldSecurityService extends ActAAAService.Base<Person> {

    @Override
    protected boolean verifyPassword(Person person, char[] chars) {
        return person.verifyPassword(chars);
    }

}
