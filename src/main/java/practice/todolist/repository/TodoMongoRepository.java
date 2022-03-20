package practice.todolist.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import practice.todolist.entity.Todo;
import reactor.core.publisher.Flux;

public interface TodoMongoRepository extends ReactiveMongoRepository<Todo, String> {
    Flux<Todo> findAllByIdAndTask(String id, String task);
}
