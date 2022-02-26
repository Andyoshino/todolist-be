package com.practicereactor;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TodosController {

    private final TodosMongoRepository todosMongoRepository;

    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public Mono<List<Todos>> getAll() {
        return todosMongoRepository.findAll()
                .collectList();
    }

    @RequestMapping(value = "/todos/{id}", method = RequestMethod.GET)
    public Mono<Todos> getByID(@PathVariable("id") String id) {
        return todosMongoRepository.findById(id);
    }

    @RequestMapping(value = "/todos", method = {RequestMethod.POST, RequestMethod.PUT})
    public Mono<Todos> save(@RequestBody Todos todos) { return todosMongoRepository.save(todos); }

    @RequestMapping(value = "/todos/{id}", method = RequestMethod.DELETE)
    public Mono<Void> delete(@PathVariable("id") String id) { return todosMongoRepository.deleteById(id); }

    @RequestMapping(value = "/todos/clear", method = RequestMethod.DELETE)
    public Mono<Void> deleteAll() { return todosMongoRepository.deleteAll(); }

}
