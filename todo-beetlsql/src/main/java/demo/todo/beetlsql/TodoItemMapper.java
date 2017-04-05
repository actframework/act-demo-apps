package demo.todo.beetlsql;

import java.util.List;

import org.beetl.sql.core.mapper.BaseMapper;

public interface TodoItemMapper extends BaseMapper<TodoItem> {
	public List<TodoItem> selectAll();
}
