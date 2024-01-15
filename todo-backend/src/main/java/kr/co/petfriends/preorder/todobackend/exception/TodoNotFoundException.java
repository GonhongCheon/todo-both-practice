package kr.co.petfriends.preorder.todobackend.exception;

public class TodoNotFoundException extends RuntimeException{
    public TodoNotFoundException(Long id) {
        super("Could not find Todo" + id);
    }
}
