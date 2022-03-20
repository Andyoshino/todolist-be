package practice.todolist.repository;


import practice.todolist.entity.Todo;
import reactor.core.publisher.Flux;

public interface TodoRepositoryCustom {
    public Flux<Todo> findCustom();
}
