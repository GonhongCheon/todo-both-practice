package kr.co.petfriends.preorder.todobackend.controller;

import kr.co.petfriends.preorder.todobackend.assembler.TodoModelAssembler;
import kr.co.petfriends.preorder.todobackend.data.Todo;
import kr.co.petfriends.preorder.todobackend.service.TodoService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class TodoController {
  private final TodoService service;
  private final TodoModelAssembler assembler;

  public TodoController(TodoService service, TodoModelAssembler assembler) {
    this.service = service;
    this.assembler = assembler;
  }

  @GetMapping("/todo/{id}")
  public EntityModel<Todo> getTodo(@PathVariable Long id) {
    Todo todo = service.one(id);
    return assembler.toModel(todo);
  }

  @GetMapping("/todos")
  public CollectionModel<EntityModel<Todo>> getTodos() {
    List<EntityModel<Todo>> todos = service.all().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

    return CollectionModel.of(todos, linkTo(methodOn(TodoController.class).getTodos()).withSelfRel());
  }

  @PostMapping("/todo")
  public ResponseEntity<?> postTodo(@RequestBody Todo todo) {
    EntityModel<Todo> entityModel = assembler.toModel(service.newTodo(todo));
    return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
  }

  @DeleteMapping("/todo/{id}")
  public ResponseEntity<?>  deleteTodo(@PathVariable Long id) {
    service.deleteTodo(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/todo/{id}")
  public ResponseEntity<?> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {

    EntityModel<Todo> entityModel = assembler.toModel(service.replaceTodo(id, todo));
    return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
  }
}
