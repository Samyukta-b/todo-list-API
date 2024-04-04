package com.example.HelloSQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cloudvendor/todo")
public class TodoItemController {

    @Autowired
    private TodoItemRepository todoItemRepository;

    @GetMapping("/{vendorId}/getTodo")
    public List<TodoItemModel> getAllTodoItems(@PathVariable Long vendorId) {
        return todoItemRepository.findByVendorId(vendorId);
    }

    @PostMapping("/{vendorId}/addTodo")
    public TodoItemModel createTodoItem(@PathVariable Long vendorId, @RequestBody TodoItemModel todoItem) {
        todoItem.setVendorId(vendorId); // Set vendorId before saving
        return todoItemRepository.save(todoItem);
    }

    @PutMapping("/{vendorId}/updateTodo/{todoId}")
    public TodoItemModel updateTodoItem(@PathVariable Long vendorId, @PathVariable Long todoId, @RequestBody TodoItemModel todoItem) {
        TodoItemModel existingTodoItem = todoItemRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("Todo item not found"));

        if (!existingTodoItem.getVendorId().equals(vendorId)) {
            throw new IllegalArgumentException("Todo item does not belong to the specified vendor");
        }

        existingTodoItem.setTaskName(todoItem.getTaskName());
        existingTodoItem.setCompleted(todoItem.isCompleted());

        return todoItemRepository.save(existingTodoItem);
    }


    @DeleteMapping("/{vendorId}/delTodo/{todoId}")
    public void deleteTodoItem(@PathVariable Long vendorId, @PathVariable Long todoId) {
        todoItemRepository.deleteById(todoId);
    }
}



