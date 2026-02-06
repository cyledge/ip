package cybot;

import cybot.task.Task;
import cybot.task.TaskList;


import java.util.Scanner;

/**
 * For printing message (UNUSED)
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



}
