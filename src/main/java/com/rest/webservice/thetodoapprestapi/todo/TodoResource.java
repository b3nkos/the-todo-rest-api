package com.rest.webservice.thetodoapprestapi.todo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TodoResource {

    private TodoHardcodedService todoHardcodedService;

    public TodoResource(TodoHardcodedService todoHardcodedService) {
        this.todoHardcodedService = todoHardcodedService;
    }

    @GetMapping("/users/{user_name}/todos")
    public List<Todo> getAllTodos(@PathVariable("user_name") String username) {
        return todoHardcodedService.findAll();
    }

    @GetMapping("/users/{user_name}/todos/{id}")
    public Todo getTodo(@PathVariable("user_name") String username, @PathVariable long id) {
        return todoHardcodedService.findById(id);
    }

    @DeleteMapping("/users/{user_name}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("user_name") String username, @PathVariable long id) {
        Todo todo = todoHardcodedService.deleteById(id);

        return todo == null ? ResponseEntity.notFound().build() : ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{user_name}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable("user_name") String username,
                                           @PathVariable long id, @RequestBody Todo todo) {
        Todo todoUpdated = todoHardcodedService.update(todo);
        return new ResponseEntity<>(todoUpdated, HttpStatus.OK);
    }

    @PostMapping("/users/{user_name}/todos")
    public ResponseEntity<Void> saveTodo(@PathVariable("user_name") String username, @RequestBody Todo todo) {
        Todo todoSaved = todoHardcodedService.save(todo);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(todoSaved.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
