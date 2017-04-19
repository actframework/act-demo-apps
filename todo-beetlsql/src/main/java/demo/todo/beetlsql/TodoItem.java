package demo.todo.beetlsql;



import javax.persistence.Entity;
import javax.persistence.Id;

import org.beetl.sql.core.annotatoin.AutoID;;
@Entity
public class TodoItem  {
   
	@Id
    private Long id;

    private String desc;

    public TodoItem() {
    }

    @AutoID //beetlsql 注解
    public Long getId() {
        return id;
    }



	public void setId(Long id) {
		this.id = id;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}

    

}
