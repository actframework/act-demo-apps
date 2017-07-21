package demo.helloworld;

import act.view.excel.JexlFunc;

@JexlFunc("util")
public class JexlUtils {
    public int sum(int a, int b) {
        return a + b;
    }
}
