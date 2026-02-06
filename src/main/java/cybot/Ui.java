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

    public String printHorizontalLine() {
        return "____________________________________________________________\n";
    }

    public String welcomeMsg() {
        StringBuilder sb = new StringBuilder();
        sb.append(printHorizontalLine());
        sb.append("Hello! I'm CYbot." + "\n");
        sb.append("What can I do for you?" + "\n");
        sb.append(printHorizontalLine());
        return sb.toString();
    }

    public String byeMsg() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bye. Hope to see you again soon!" + "\n");
        sb.append(printHorizontalLine());
        return sb.toString();
    }

    /**
     * show the error msg to the user
     * @param msg error msg
     */
    public String showError(String msg) {
        return msg + "\n";
    }

    /**
     * Shows that cannot load the file
     */
    public String showLoadingError() {
        return "Error loading tasks. Starting with empty list\n";
    }

    // deleted
    public String showTaskAdded(Task task, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:" + "\n");
        sb.append(task + "\n");
        sb.append(printNumTask(size) + "\n");
        return sb.toString();
    }

    // deleted
    public String showTaskDeleted(Task task, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task: " + "\n");
        sb.append(task + "\n");
        sb.append(printNumTask(size) + "\n");
        return sb.toString();
    }

    private static String printNumTask(int size) {
        return String.format("Now you have %d tasks in the list.\n", size);
    }

    public String showTaskList(TaskList tasks) {
        if (tasks.size() == 0) {
            return "No task in your list!\n";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append(i + ". " + tasks.get(i - 1) + "\n");
        }
        return sb.toString();
    }

    public String showMark(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append(task + "\n");
        return sb.toString();
    }


    public String showUnmark(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Ok, I've marked this task as not done yet:\n");
        sb.append(task + "\n");
        return sb.toString();
    }



}
