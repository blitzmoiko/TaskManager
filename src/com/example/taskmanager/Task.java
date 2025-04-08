package com.example.taskmanager;

import java.time.LocalDate;

public record Task(
        String id,
        String title,
        String description,
        LocalDate dueDate,
        boolean isCompleted
) {}
