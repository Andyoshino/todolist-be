package practice.todolist.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import practice.todolist.entity.Todo;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Component
public class TodoRepositoryCustomImpl implements TodoRepositoryCustom {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Flux<Todo> findCustom() {
        Query queryC = new Query();
        queryC.addCriteria(Criteria.where("task").regex(".*" + "mem" + ".*", "i"))
                //.with(PageRequest.of(curPage, pageSize))
                .with(Sort.by(Sort.Direction.DESC, "Task"))
                .limit(4);
        return reactiveMongoTemplate.find(queryC, Todo.class);

    }

}
