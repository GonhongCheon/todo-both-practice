package kr.co.petfriends.preorder.todobackend.service;

import kr.co.petfriends.preorder.todobackend.data.Todo;
import kr.co.petfriends.preorder.todobackend.exception.TodoNotFoundException;
import kr.co.petfriends.preorder.todobackend.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TodoService {
  private final TodoRepository repository;

  public TodoService(TodoRepository repository) {
    this.repository = repository;
  }

  @Transactional(readOnly = true)
  public List<Todo> all() {
    return repository.findAll();
  }

  @Transactional(readOnly = true)
  public Todo one(Long id) {
    return repository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
  }

  @Transactional
  public Todo newTodo(Todo newTodo) {
    return repository.save(newTodo);
  }


  @Transactional
  public void deleteTodo(Long id) {
    repository.deleteById(id);
  }

  @Transactional
  public Todo replaceTodo(Long id, Todo newTodo) {
    return repository.findById(id)
            .map(todo -> {
              todo.setContent(newTodo.getContent());
              todo.setStatus(newTodo.getStatus());
              return repository.save(todo);
            }).orElseGet(() -> {
              newTodo.setId(id);
              return repository.save(newTodo);
            });
  }
}
