package demo.helloworld;

import org.osgl.$;

import javax.inject.Singleton;

@Singleton
public class LoadManager {
    public String payload() {
        return "Good morning " + $.ms();
    }
}
