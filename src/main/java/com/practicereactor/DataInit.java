package com.practicereactor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements CommandLineRunner {

    private final TodosMongoRepository todosMongoRepository;

    public DataInit(TodosMongoRepository todosMongoRepository) {
        this.todosMongoRepository = todosMongoRepository;
    }

    @Override
    public void run(String[] args) {
        System.out.println("initializing o_o ...");
    }

}