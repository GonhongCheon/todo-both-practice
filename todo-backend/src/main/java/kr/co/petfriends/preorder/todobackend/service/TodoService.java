package kr.co.petfriends.preorder.todobackend.service;

import kr.co.petfriends.preorder.todobackend.data.Todo;
import kr.co.petfriends.preorder.todobackend.repository.TodoRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
  private TodoRepository todoRepository;

  @Transactional(readOnly = true)
  public Todo getTodo(Long id) {
    return todoRepository.findById(id).orElseThrow();
  }

  @Transactional(readOnly = true)
  public List<Todo> getTodos() {
    return todoRepository.findAll();
  }
  @Transactional
  public void deleteTodo(Long id) {
    todoRepository.deleteById(id);
  }
  @Transactional
  public void saveTodo(Todo newTodo) {
    todoRepository.save(newTodo);
  }
  @Transactional
  public void updateTodo(Long id, Todo newTodo) {
    Todo todo = todoRepository.findById(id).orElseThrow();
    todo.setContent(newTodo.getContent());
    todoRepository.save(todo);
  }
}
