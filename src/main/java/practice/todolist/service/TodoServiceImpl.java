package practice.todolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import practice.todolist.dto.Response;
import practice.todolist.entity.Todo;
import practice.todolist.repository.TodoMongoRepository;
import practice.todolist.repository.TodoRepositoryCustom;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoMongoRepository todoMongoRepository;
    private final TodoRepositoryCustom todoRepositoryCustom;

    @Override
    public Mono<List<List<Todo>>> getAll(Integer pageSize, String sort, String filter) {
        return null;
    }

    @Override
    public Mono<List<Todo>> getAllCustom() {
        return todoRepositoryCustom.findCustom().collectList();
    }


    @Override
    public Mono<Todo> getById(String id) {
        return todoMongoRepository
                .findById(id);
    }

    @Override
    public Mono<Boolean> edit(Todo todo) {
        return getById(todo.getId())
                .hasElement()
                .doOnNext(elm -> {
                    if (elm) todoMongoRepository.save(todo).subscribe();
                });
    }

    @Override
    public Mono<Todo> add(Todo todo) {
        return todoMongoRepository.save(todo);
    }

    @Override
        public Mono<Boolean> deleteById(String id) {
        return this.getById(id)
                .hasElement()
                .doOnNext(elm -> {
                    if (elm) todoMongoRepository.deleteById(id).subscribe();
                });
    }

    @Override
    public Mono<Void> deleteAll() {
         return todoMongoRepository.deleteAll();
    }
}
