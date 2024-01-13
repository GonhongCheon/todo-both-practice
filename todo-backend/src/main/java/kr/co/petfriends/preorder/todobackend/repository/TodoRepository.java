package kr.co.petfriends.preorder.todobackend.repository;

import kr.co.petfriends.preorder.todobackend.data.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
