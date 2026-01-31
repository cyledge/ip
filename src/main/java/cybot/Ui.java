package cybot;

import cybot.task.Task;
import cybot.task.TaskList;


import java.util.Scanner;

/**
 * For printing message
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public void welcomeMsg() {
        printHorizontalLine();
        System.out.println("Hello! I'm CYbot.");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public void byeMsg() {
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    /**
     * show the error msg to the user
     * @param msg error msg
     */
    public void showError(String msg) {
        System.out.println(msg);
    }

    /**
     * Shows that cannot load the file
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting with empty list");
    }

    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        printNumTask(size);
    }

    public void showTaskDeleted(Task task, int size) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task);
        printNumTask(size);
    }

    private static void printNumTask(int size) {
        System.out.println(String.format("Now you have %d tasks in the list.", size));
    }

    public void showTaskList(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println("No task in your list!");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i - 1));
        }
    }

    public void showMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }


    public void showUnmark(Task task) {
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(task);
    }



}
