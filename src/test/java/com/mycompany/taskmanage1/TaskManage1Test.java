/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.taskmanage1;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskManage1Test {

    @BeforeEach
    public void setUp() {
        // Clear existing data in TaskManage arrays
        TaskManage1.tasks.clear();
        TaskManage1.developers.clear();
        TaskManage1.taskNames.clear();
        TaskManage1.taskDurations.clear();
        TaskManage1.taskStatuses.clear();

        // Add mock tasks
        TaskManage1.developers.add("Mike Smith");
        TaskManage1.developers.add("Edward Harrington");
        TaskManage1.developers.add("Samantha Paulson");
        TaskManage1.developers.add("Glenda Oberholzer");

        TaskManage1.taskNames.add("Create Login");
        TaskManage1.taskNames.add("Add Features");
        TaskManage1.taskNames.add("Fix Bugs");
        TaskManage1.taskNames.add("Create Reports");

        TaskManage1.taskDurations.add(5);
        TaskManage1.taskDurations.add(8);
        TaskManage1.taskDurations.add(7);
        TaskManage1.taskDurations.add(11);

        TaskManage1.taskStatuses.add("Done");
        TaskManage1.taskStatuses.add("Doing");
        TaskManage1.taskStatuses.add("To Do");
        TaskManage1.taskStatuses.add("Done");
    }

    @Test
    public void testDeveloperArrayCorrectlyPopulated() {
        String[] expectedDevelopers = {
            "Mike Smith",
            "Edward Harrington",
            "Samantha Paulson",
            "Glenda Oberholzer"
        };
        assertArrayEquals(expectedDevelopers, TaskManage1.developers.toArray(), "Developer array was not correctly populated!");
    }

    @Test
    public void testDisplayLongestDuration() {
        int longestIndex = 0;
        for (int i = 1; i < TaskManage1.taskDurations.size(); i++) {
            if (TaskManage1.taskDurations.get(i) > TaskManage1.taskDurations.get(longestIndex)) {
                longestIndex = i;
            }
        }
        String expectedOutput = "Glenda Oberholzer,11";
        String actualOutput = TaskManage1.developers.get(longestIndex) + "," + TaskManage1.taskDurations.get(longestIndex);
        assertEquals(expectedOutput, actualOutput, "Task with the longest duration not displayed correctly!");
    }

    @Test
    public void testSearchTaskByName() {
        
        
        String searchTask = "Create Login";
        int taskIndex = TaskManage1.taskNames.indexOf(searchTask);

        if (taskIndex >= 0) {
            String expectedOutput = "Mike Smith,Create Login";
            String actualOutput = TaskManage1.developers.get(taskIndex) + "," + TaskManage1.taskNames.get(taskIndex);
            assertEquals(expectedOutput, actualOutput, "Task search did not return the correct result!");
        } else {
            fail("Task not found.");
        }
    }

   @Test
public void testSearchTasksByDeveloper() {
    String searchDeveloper = "Samantha Paulson";
    ArrayList<String> foundTasks = new ArrayList<>();

    // Debug: Check all developers
    System.out.println("Developers: " + TaskManage1.developers);

    // Populate foundTasks for the given developer
    for (int i = 0; i < TaskManage1.developers.size(); i++) {
        if (TaskManage1.developers.get(i).equals(searchDeveloper)) {
            foundTasks.add(TaskManage1.taskNames.get(i));
        }
    }

    // Debug: Print found tasks
    System.out.println("Found tasks for " + searchDeveloper + ": " + foundTasks);

    // Expected tasks
    ArrayList<String> expectedTasks = new ArrayList<>();
    expectedTasks.add("Fix Bugs");

    // Debug: Print expected tasks
    System.out.println("Expected tasks: " + expectedTasks);

    // Assert equality
    assertEquals(expectedTasks, foundTasks, "Tasks assigned to the developer were not found correctly!");
}


    @Test
    public void testDeleteTaskByName() {
        String deleteTask = "Create Reports";
        int taskIndex = TaskManage1.taskNames.indexOf(deleteTask);

        if (taskIndex >= 0) {
            TaskManage1.taskNames.remove(taskIndex);
            TaskManage1.developers.remove(taskIndex);
            TaskManage1.taskDurations.remove(taskIndex);
            TaskManage1.taskStatuses.remove(taskIndex);
            assertFalse(TaskManage1.taskNames.contains(deleteTask), "Task was not deleted successfully!");
        } else {
            fail("Task not found.");
        }
    }
    
}



    