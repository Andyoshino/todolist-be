package practice.todolist.service;


import practice.todolist.dto.Response;
import practice.todolist.entity.Todo;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TodoService {

    public Mono<List<List<Todo>>> getAll(Integer pageSize, String sort, String filter);

    public Mono<List<Todo>> getAllCustom();

    public Mono<Todo> getById(String id);
    public Mono<Boolean> edit(Todo todo);
    public Mono<Todo> add(Todo todo);
    public Mono<Boolean> deleteById(String id);
    public Mono<Void> deleteAll();

}
