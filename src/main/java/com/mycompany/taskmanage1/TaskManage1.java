/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.taskmanage1;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author RC_Student_lab
 */

public class TaskManage1 {
    //Scanner for user input 
    static Scanner scanner = new Scanner(System.in);
    //----------------PART 3-POE ------------------------
    
    //Arrays to store Task Details
    static ArrayList<Task> tasks = new ArrayList<>();
    static ArrayList<String> developers = new ArrayList<>();
    static ArrayList<String> taskNames = new ArrayList<>();
    static ArrayList<String> taskIDs = new ArrayList<>();
    static ArrayList<Integer> taskDurations = new ArrayList<>();
    static ArrayList<String> taskStatuses = new ArrayList<>();
/**
 * *Main Method -Entry Point of the application 
     * @param args
 */
    public static void main(String[] args) {
        System.out.println("Welcome to EasyKanban");
        int option; //User's menu choice 
        
        
        //Loop until the user chooses to quit 
        do {
            //Display menu options
            System.out.println("\nMenu Options:");
            System.out.println("1) Add tasks");
            System.out.println("2) Show report");
            System.out.println("3) Display tasks with status 'done'");
            System.out.println("4) Display developer and duration of the task with the longest duration");
            System.out.println("5) Search for a task by name and display task details");
            System.out.println("6) Search for tasks assigned to a developer and display details");
            System.out.println("7) Delete a task by name");
            System.out.println("8) Quit");
            System.out.print("Enter your option: ");
            option = scanner.nextInt();
            
            //Perform the selected action 
            switch (option) {
                case 1:
                    addTask(); //Add a new task 
                    break;
                case 2:
                    displayReport(); // Display all tasks
                    break;
                case 3:
                    displayTasksWithStatus("Done");// Display tasks with status "Done"
                    break;
                case 4:
                    displayTaskWithLongestDuration(); //Display task with the longest duration 
                    break;
                case 5:
                    searchTaskByName(); //Search for a task by its name 
                    break;
                case 6:
                    searchTasksByDeveloper(); // Search for tasks by Developer
                    break;
                case 7:
                    deleteTaskByName(); //Delete a task by its name 
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (option != 8); //Repeat until the user chooses to quit 
    }
/**
 * *Adds a new task to the system 
 */
    public static void addTask() {
        scanner.nextLine(); // Clear the scanner buffer
        System.out.print("Enter task name: ");
        String name = scanner.nextLine(); //Read task name 
        taskNames.add(name); //Add task name to the array 

        Task task = new Task(); // create a new task object
        task.name = name; //Set task name 
        task.number = tasks.size() + 1; //Assign task number based on list size  

        //Input and validate task description
        System.out.print("Enter task description: ");
        task.description = scanner.nextLine();
        if (task.description.length() > 50) {
            System.out.println("Please enter a task description of less than 50 characters");
            return; //Stop execution if the description is invalid 
        }

        //Input Developer Details 
        System.out.print("Enter developer first name: ");
        task.developerFirstName = scanner.nextLine();
        System.out.print("Enter developer last name: ");
        task.developerLastName = scanner.nextLine();
        developers.add(task.developerFirstName + " " + task.developerLastName);

        //Input and store task duration 
        System.out.print("Enter task duration in hours: ");
        task.duration = scanner.nextInt();
        taskDurations.add(task.duration); //Add duration to the array 

        //Generate a unique task ID and store it 
        task.id = (task.name.substring(0, 2) + ":" + task.number + ":" + task.developerLastName.substring(task.developerLastName.length() - 3)).toUpperCase();
        taskIDs.add(task.id);

        //Input and validate task status
        System.out.println("Select task status:");
        System.out.println("1) To Do");
        System.out.println("2) Done");
        System.out.println("3) Doing");
        int statusOption = scanner.nextInt();
        switch (statusOption) {
            case 1:
                task.status = "To Do";
                break;
            case 2:
                task.status = "Done";
                break;
            case 3:
                task.status = "Doing";
                break;
            default:
                System.out.println("Invalid option. Try again.");
                return; // Stop execution if the status is invalid 
        }
        taskStatuses.add(task.status); //Add status to the array 

        tasks.add(task); //Add task to the list of tasks 
        System.out.println("Task successfully captured with ID: " + task.id);
    }
    
    /** 
     * Displays a full report of all tasks 
     */
    
    public static void displayReport() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("Task Name: " + taskNames.get(i));
            System.out.println("Developer: " + developers.get(i));
            System.out.println("Task Duration: " + taskDurations.get(i));
            System.out.println("Task ID: " + taskIDs.get(i));
            System.out.println("Task Status: " + taskStatuses.get(i));
            System.out.println();
        }
    }
    /**
     * Displays tasks filtered by their status 
     * @param status The status filter tasks by 
     */

    public static void displayTasksWithStatus(String status) {
        for (int i = 0; i < tasks.size(); i++) {
            if (taskStatuses.get(i).equalsIgnoreCase(status)) {
                System.out.println("Task Name: " + taskNames.get(i));
                System.out.println("Developer: " + developers.get(i));
                System.out.println("Task Duration: " + taskDurations.get(i));
                System.out.println("Task ID: " + taskIDs.get(i));
                System.out.println("Task Status: " + taskStatuses.get(i));
                System.out.println();
            }
        }
    }
    
    /**
     * Displays the task with the longest duration 
     */
    public static void displayTaskWithLongestDuration() {
        int longestDurationIndex = 0;
        for (int i = 1; i < tasks.size(); i++) {
            if (taskDurations.get(i) > taskDurations.get(longestDurationIndex)) {
                longestDurationIndex = i;
            }
        }
        System.out.println("Task Name: " + taskNames.get(longestDurationIndex));
        System.out.println("Developer: " + developers.get(longestDurationIndex));
        System.out.println("Task Duration: " + taskDurations.get(longestDurationIndex));
        System.out.println("Task ID: " + taskIDs.get(longestDurationIndex));
        System.out.println("Task Status: " + taskStatuses.get(longestDurationIndex));
    }
    
    /**
     * Searches for a task by its name and displays its details 
     */
    public static void searchTaskByName() {
        scanner.nextLine();
        System.out.print("Enter the task name to search: ");
        String searchName = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(searchName)) {
                System.out.println("Task Name: " + taskNames.get(i));
                System.out.println("Developer: " + developers.get(i));
                System.out.println("Task Duration: " + taskDurations.get(i));
                System.out.println("Task ID: " + taskIDs.get(i));
                System.out.println("Task Status: " + taskStatuses.get(i));
                System.out.println();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Task not found.");
        }
    }
    /** 
     * Searches for all tasks assigned to a developer 
     */

    public static void searchTasksByDeveloper() {
        scanner.nextLine();
        System.out.print("Enter the developer name to search: ");
        String searchDeveloper = scanner.nextLine();
        boolean found = false;// Flag to indicate if any tasks were found 
        for (int i = 0; i < tasks.size(); i++) {
            if (developers.get(i).equalsIgnoreCase(searchDeveloper)) {
                System.out.println("Task Name: " + taskNames.get(i));
                System.out.println("Task Status: " + taskStatuses.get(i));
                System.out.println();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks assigned to the specified developer.");
        }
    }
    
    /**
     * Deletes a task by its name 
     */

    public static void deleteTaskByName() {
        scanner.nextLine();
        System.out.print("Enter the task name to delete: ");
        String deleteName = scanner.nextLine();
        boolean found = false; // Flag 
        for (int i = 0; i < tasks.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(deleteName)) {
                tasks.remove(i);
                taskNames.remove(i);
                developers.remove(i);
                taskDurations.remove(i);
                taskIDs.remove(i);
                taskStatuses.remove(i);
                System.out.println("Task successfully deleted.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Task not found.");
        }
    }
    
    public static String generateReport() {
    StringBuilder report = new StringBuilder();
    for (int i = 0; i < tasks.size(); i++) {
        report.append("Task Name: ").append(taskNames.get(i)).append("\n")
              .append("Developer: ").append(developers.get(i)).append("\n")
              .append("Task Duration: ").append(taskDurations.get(i)).append(" hours\n")
              .append("Task ID: ").append(taskIDs.get(i)).append("\n")
              .append("Task Status: ").append(taskStatuses.get(i)).append("\n\n");
    }
    return report.toString();
}


    /** 
     * Represents a Task Object
     */
    
    static class Task {
        String name; //Task Name 
        String description; //Task Description 
        String developerFirstName; //Developer's first name 
        String developerLastName;// Developer's last name 
        int duration;// Task duration in hours 
        String id; //Task ID 
        String status; //Task status 
        int number; //Task number 
    }
}

