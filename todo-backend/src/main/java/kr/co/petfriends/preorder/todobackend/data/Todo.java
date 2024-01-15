package kr.co.petfriends.preorder.todobackend.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Setter
@Getter
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private TodoStatus status;

    public Todo() {

    }

    public Todo(String content, TodoStatus status) {
        this.content = content;
        this.status = status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.content, this.status);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Todo))
            return false;
        Todo todo = (Todo) obj;
        return Objects.equals(this.id, todo.id) && Objects.equals(this.content, todo.content)
                && Objects.equals(this.status, todo.status);
    }

    @Override
    public String toString() {
        return "Todo{" + "id=" + this.id + ", content='" + this.content + '\'' + ", status='" + this.status + '\'' + '}';
    }
}
