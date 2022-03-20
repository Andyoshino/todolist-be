package practice.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import practice.todolist.entity.Todo;
import reactor.core.publisher.Mono;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {
    private Mono<List<Todo>> listTodo;
    private Integer curPage;
    private Integer pageSize;
    private Integer lastPage;
}
