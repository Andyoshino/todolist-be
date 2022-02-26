package com.practicereactor;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TodosMongoRepository extends ReactiveMongoRepository<Todos, String> {}
