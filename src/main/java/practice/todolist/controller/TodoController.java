package practice.todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.todolist.dto.Response;
import practice.todolist.entity.Todo;
import practice.todolist.service.TodoService;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public Mono<List<Todo>> getAll(@RequestParam(required = false) String pageSize,
                                   @RequestParam(required = false) String curPage,
                                   @RequestParam(required = false) String sort,
                                   @RequestParam(required = false) String filter) {

        return todoService.getAllCustom();
    }

    @RequestMapping(value = "/todos/{id}", method = RequestMethod.GET)
    public Mono<ResponseEntity<Mono<Todo>>> getByID(@PathVariable("id") String id) {
        return todoService.getById(id)
                .hasElement()
                .map(elm -> new ResponseEntity<>(elm ? todoService.getById(id) : Mono.just(new Todo()), elm ? HttpStatus.OK : HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/todos", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Mono<ResponseEntity<Mono<Todo>>> edit(@RequestBody Todo todo) {
        if (todo.getId() == null || todo.getId().isEmpty()
                || todo.getSchedule() == null || todo.getSchedule().toString().isEmpty() || todo.getTask() == null || todo.getTask().isEmpty())
            return Mono.just(new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
        return todoService.edit(todo)
                .map(elm -> new ResponseEntity<>(elm ? todoService.getById(todo.getId()) : null, elm ? HttpStatus.OK : HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/todos", method = RequestMethod.POST)
    public Mono<ResponseEntity<Todo>> add(@RequestBody Todo todo) {
        if (todo.getSchedule() == null || todo.getTask() == null || todo.getId() != null)
            return Mono.just(new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));

        return todoService.add(todo)
                .map(elm -> new ResponseEntity<>(elm, HttpStatus.OK));
    }

    @RequestMapping(value = "/todos/{id}", method = RequestMethod.DELETE)
    public Mono<ResponseEntity<String>> delete(@PathVariable("id") String id) {
        return todoService.deleteById(id)
                .map(elm -> new ResponseEntity<>("Delete " + (elm ? "Successful" : "Failed"), (elm ? HttpStatus.OK : HttpStatus.NOT_FOUND)));
    }

    @RequestMapping(value = "/todos/clear", method = RequestMethod.DELETE)
    public Mono<ResponseEntity<String>> deleteAll() {
        return todoService.deleteAll()
                .hasElement()
                .map(elm -> new ResponseEntity<>("Clear Successful", HttpStatus.OK));
    }

}
