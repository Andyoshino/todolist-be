package practice.todolist;

import com.mongodb.reactivestreams.client.MongoClient;
import lombok.RequiredArgsConstructor;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import practice.todolist.entity.Todo;
import practice.todolist.repository.TodoRepositoryCustom;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@SpringBootTest
@RequiredArgsConstructor
class TodolistApplicationTests {

    @Autowired
    public ReactiveMongoTemplate reactiveMongoTemplate;

    Mono<String> sample(String message) {
        System.out.println("Hello...");
        return Mono.just(message);
    }

    @Test
    public void bufferTest() throws InterruptedException {

      Flux<String> fruitFlux = Flux.just("apple", "orange");
      fruitFlux.subscribeOn(Schedulers.newParallel("ThreadA"))
              .doOnNext(elm -> {
                try {
                  System.out.println("Tidurin " + Thread.currentThread().getName());
                  Thread.sleep(5000);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
                System.out.println(elm);
              })
                  .subscribe();
      System.out.println("hello main thread");
      Thread.currentThread().sleep(15000);
      System.out.println("ini print terakhir");
    }

}
