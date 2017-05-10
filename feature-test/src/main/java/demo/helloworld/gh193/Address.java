package demo.helloworld.gh193;

import act.data.annotation.Data;
import act.util.SimpleBean;

@Data
public class Address implements SimpleBean {

    public String streetNo;
    public String streetName;
    public String suburb;
    public String state;

}
