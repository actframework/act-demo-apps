package act.fsa.todo;

import act.db.morphia.MorphiaDao;
import org.bson.types.ObjectId;

public class TodoItemDao extends MorphiaDao<ObjectId, TodoItem, TodoItemDao> {
    public TodoItemDao() {
        super(TodoItem.class);
    }
}
