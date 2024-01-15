package kr.co.petfriends.preorder.todobackend.assembler;

import kr.co.petfriends.preorder.todobackend.controller.TodoController;
import kr.co.petfriends.preorder.todobackend.data.Todo;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TodoModelAssembler implements RepresentationModelAssembler<Todo, EntityModel<Todo>> {
    @Override
    public EntityModel<Todo> toModel(Todo todo) {
        return EntityModel.of(todo,
                linkTo(methodOn(TodoController.class).getTodo(todo.getId())).withSelfRel(),
                linkTo(methodOn(TodoController.class).getTodos()).withRel("todos"));
    }
}
