import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CYbot {
    private static String myName = "CYbot";
    private static ArrayList<Task> taskList = new ArrayList<Task>();
    private static ArrayList<String> cmdList = new ArrayList<String>(Arrays.asList("list", "mark", "unmark"));

    private static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    private static void welcomeMsg() {
        printHorizontalLine();
        System.out.println("Hello! I'm " + myName);
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    private static void byeMsg() {
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    private static void printList() {
        for (int i = 1; i <= taskList.toArray().length; i++) {
            System.out.println(i + ". " + taskList.get(i-1));
        }
    }

    private static void addTask(String task) {
        taskList.add(new Task(task));
        System.out.println("added: " + task);
    }

    private static boolean isCommand(String userInput) {
        String[] inputStr = userInput.split("\\s+");
        if (cmdList.contains(inputStr[0])) {
            return true;
        }
        return false;
    }

    private static String[] breakInput(String command) {
        return command.split("\\s+");
    }

    private static void mark(int index) {
        taskList.get(index).markDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(index));
    }

    private static void unmark(int index) {
        taskList.get(index).unmarkDone();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(taskList.get(index));
    }

    private static void callCommand(String userInput) {
        String[] wholeCmd = breakInput(userInput);
        String command = wholeCmd[0];
        switch (command) {
            case "list":
                printList();
                break;
            case "mark":
                mark(Integer.parseInt(wholeCmd[1]) - 1);
                break;
            case "unmark":
                unmark(Integer.parseInt(wholeCmd[1]) - 1);
                break;

        }
    }

    private static void processMsg(Scanner scanner) {
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            printHorizontalLine();
            if (isCommand(userInput)) {
                callCommand(userInput);
            } else {
                addTask(userInput);
            }

            printHorizontalLine();
        }
    }

    public static void main(String[] args) {
    welcomeMsg();
    Scanner scanner = new Scanner(System.in);
    processMsg(scanner);

    byeMsg();

    }
}
