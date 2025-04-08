package main.java.com.example.taskmanager;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CLI {
    private final TaskManager manager = new TaskManager();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Task Manager CLI (Java 17)");

        while (true) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addTask();
                case "2" -> listTasks();
                case "3" -> markTaskComplete();
                case "4" -> deleteTask();
                case "5" -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n1. Add Task");
        System.out.println("2. List Tasks");
        System.out.println("3. Mark Task Complete");
        System.out.println("4. Delete Task");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    private void addTask() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        LocalDate dueDate = null;
        while (dueDate == null) {
            System.out.print("Due Date (YYYY-MM-DD): ");
            try {
                dueDate = LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format!");
            }
        }

        manager.addTask(title, description, dueDate);
        System.out.println("Task added!");
    }

    private void listTasks() {
        System.out.println("\nAll Tasks:");
        manager.getAllTasks().forEach(task ->
                System.out.printf(
                        "[%s] %s (Due: %s)%n",
                        task.isCompleted() ? "✓" : " ",
                        task.title(),
                        task.dueDate()
                )
        );
    }

    private void markTaskComplete() {
        System.out.print("Enter task ID to mark complete: ");
        String id = scanner.nextLine();
        boolean success = manager.markTaskComplete(id);
        System.out.println(success ? "Task marked complete!" : "Task not found.");
    }

    private void deleteTask() {
        System.out.print("Enter task ID to delete: ");
        String id = scanner.nextLine();
        boolean success = manager.deleteTask(id);
        System.out.println(success ? "Task deleted!" : "Task not found.");
    }
}