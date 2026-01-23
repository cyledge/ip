import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CYbot {
    /*
    enum Command {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT
    }
     */

    private static String myName = "CYbot";
    private static ArrayList<Task> taskList = new ArrayList<Task>();
    private static ArrayList<String> cmdList = new ArrayList<String>(Arrays.asList("list", "mark", "unmark", "todo", "deadline", "event"));

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
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(i + ". " + taskList.get(i-1));
        }
    }

    // overrided (overload)
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

    private static void addTask(Task task) {
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
    }

    private static void todo(String name) {
        Task newTask = new Todo(name);
        addTask(newTask);
    }

    private static void deadline(String command) {
        int byIndex = command.indexOf(" /by ");
        String name = command.substring(0, byIndex);
        String by = command.substring(byIndex + 5);
        Task newTask = new Deadline(name, by);
        addTask(newTask);
    }

    private static void event(String command) {
        int fromIndex = command.indexOf(" /from ");
        int toIndex = command.indexOf(" /to ");
        String name = command.substring(0, fromIndex);
        String from = command.substring(fromIndex + 7, toIndex);
        String to = command.substring(toIndex + 5);
        Task newTask = new Event(name, from, to);
        addTask(newTask);
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
            case "todo":
                todo(userInput.substring(5));
                break;
            case "deadline":
                deadline(userInput.substring(9));
                break;
            case "event":
                event(userInput.substring(6));
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
