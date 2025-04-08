package com.example.taskmanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();

    // Add a new task (lambda for ID generation)
    public void addTask(String title, String description, LocalDate dueDate) {
        tasks.add(new Task(
                UUID.randomUUID().toString(),
                title,
                description,
                dueDate,
                false
        ));
    }

    // Get all tasks (Stream)
    public List<Task> getAllTasks() {
        return tasks.stream().toList();
    }

    // Filter tasks by completion status (Stream + Predicate lambda)
    public List<Task> getTasksByStatus(boolean isCompleted) {
        return tasks.stream()
                .filter(task -> task.isCompleted() == isCompleted)
                .collect(Collectors.toList());
    }

    // Mark task as complete (Stream + Optional)
    public boolean markTaskComplete(String id) {
        Optional<Task> task = tasks.stream()
                .filter(t -> t.id().equals(id))
                .findFirst();

        task.ifPresent(t -> tasks.set(
                tasks.indexOf(t),
                new Task(t.id(), t.title(), t.description(), t.dueDate(), true)
        ));

        return task.isPresent();
    }

    // Delete a task (Stream + Predicate)
    public boolean deleteTask(String id) {
        return tasks.removeIf(task -> task.id().equals(id));
    }
}