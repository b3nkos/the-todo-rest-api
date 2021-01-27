package com.rest.webservice.thetodoapprestapi.todo;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TodoHardcodedService {

    private List<Todo> todoList;

    public TodoHardcodedService() {
        this.todoList = List.of(
                new Todo(123, "b3nkos", "Buy a standing desk", Date.from(Instant.now()), false),
                new Todo(1239766, "b3nkos", "Learn Angular", Date.from(Instant.now()), false),
                new Todo(96322, "b3nkos", "Learn Spring Boot", Date.from(Instant.now()), false),
                new Todo(9725, "b3nkos", "Learn testing", Date.from(Instant.now()), false)
        );
    }

    public List<Todo> findAll() {
        return todoList;
    }

    public Todo findById(long id) {
        return todoList.stream().filter(todo -> todo.getId() == id).findFirst().orElseThrow();
    }

    private void addTodo(Todo todo) {
        todoList = Stream.concat(todoList.stream(), Stream.of(todo))
                .collect(Collectors.toList());
    }

    public Todo save(Todo todo) {
        addTodo(todo);

        return todo;
    }

    public Todo deleteById(long id) {
        Todo todoFound = findById(id);
        Predicate<Todo> todoFilter = todo -> todo.getId() != todoFound.getId();
        todoList = todoList.stream().filter(todoFilter).collect(Collectors.toList());

        return todoFound;
    }

    public Todo update(Todo todo) {
        deleteById(todo.getId());
        addTodo(todo);
        return todo;
    }
}
