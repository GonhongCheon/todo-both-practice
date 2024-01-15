package kr.co.petfriends.preorder.todobackend.config;

import kr.co.petfriends.preorder.todobackend.data.Todo;
import kr.co.petfriends.preorder.todobackend.data.TodoStatus;
import kr.co.petfriends.preorder.todobackend.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDataBase(TodoRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Todo("해야 할 일", TodoStatus.TODO)));
            log.info("Preloading " + repository.save(new Todo("진행 중", TodoStatus.PROCEEDING)));
            log.info("Preloading " + repository.save(new Todo("완료", TodoStatus.DONE)));
        };
    }
}
