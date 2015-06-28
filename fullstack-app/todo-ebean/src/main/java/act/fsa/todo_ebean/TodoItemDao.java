package act.fsa.todo_ebean;

import act.db.ebean.EbeanDao;

public class TodoItemDao extends EbeanDao<Long, TodoItem, TodoItemDao> {
    public TodoItemDao() {
        super(TodoItem.class);
    }
}
