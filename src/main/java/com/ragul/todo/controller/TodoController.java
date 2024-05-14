package com.ragul.todo.controller;

import com.ragul.todo.model.Todo;
import com.ragul.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("todos", todoRepository.findAll());
        return "index";
    }

    @GetMapping("/todo/new")
    public String newTodoForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "edit";
    }

    @PostMapping("/todo")
    public String saveTodo(@ModelAttribute Todo todo) {
        todoRepository.save(todo);
        return "redirect:/";
    }

    @GetMapping("/todo/edit/{id}")
    public String editTodoForm(@PathVariable("id") Long id, Model model) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid todo Id:" + id));
        model.addAttribute("todo", todo);
        return "edit";
    }

    @GetMapping("/todo/delete/{id}")
    public String deleteTodo(@PathVariable("id") Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid todo Id:" + id));
        todoRepository.delete(todo);
        return "redirect:/";
    }
}
