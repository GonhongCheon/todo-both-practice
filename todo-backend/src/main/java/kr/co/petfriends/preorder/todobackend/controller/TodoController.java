package kr.co.petfriends.preorder.todobackend.controller;

import kr.co.petfriends.preorder.todobackend.data.Todo;
import kr.co.petfriends.preorder.todobackend.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class TodoController {
  private TodoService todoService;

  @GetMapping("/todo/{id}")
  public Todo getTodo(@PathVariable Long id) {
    return todoService.getTodo(id);
  }

  @GetMapping("/todos")
  public List<Todo> getTodos() {
    return todoService.getTodos();
  }

  @PostMapping("/todo")
  public void postTodo(@RequestBody Todo todo) {
    todoService.saveTodo(todo);
  }

  @DeleteMapping("/todo/{id}")
  public void deleteTodo(@PathVariable Long id) {
    todoService.deleteTodo(id);
  }

  @PutMapping("/todo/{id}")
  public void updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
    todoService.updateTodo(id, todo);
  }
}
