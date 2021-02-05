package com.rest.webservice.thetodoapprestapi.todo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TodoJPAResource {

    private TodoHardcodedService todoHardcodedService;
    private TodoJPARepository todoJPARepository;

    public TodoJPAResource(TodoHardcodedService todoHardcodedService, TodoJPARepository todoJPARepository) {
        this.todoHardcodedService = todoHardcodedService;
        this.todoJPARepository = todoJPARepository;
    }

    @GetMapping("/jpa/users/{user_name}/todos")
    public List<Todo> getAllTodos(@PathVariable("user_name") String username) {
        return todoJPARepository.findByUsername(username);
    }

    @GetMapping("/jpa/users/{user_name}/todos/{id}")
    public Todo getTodo(@PathVariable("user_name") String username, @PathVariable long id) {
        return todoJPARepository.findById(id).get();
    }

    @DeleteMapping("/jpa/users/{user_name}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("user_name") String username, @PathVariable long id) {
        todoJPARepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/jpa/users/{user_name}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable("user_name") String username,
                                           @PathVariable long id, @RequestBody Todo todo) {
        Todo todoUpdated = todoJPARepository.save(todo);
        return new ResponseEntity<>(todoUpdated, HttpStatus.OK);
    }

    @PostMapping("/jpa/users/{user_name}/todos")
    public ResponseEntity<Void> saveTodo(@PathVariable("user_name") String username, @RequestBody Todo todo) {
        todo.setUsername(username);
        Todo todoSaved = todoJPARepository.save(todo);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(todoSaved.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
