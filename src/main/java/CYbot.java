import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CYbot {
    /*
    enum Command {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT
    }
     */


    private static final String PATH_FILE = "./data/cybot.txt";
    private static ArrayList<String> cmdList = new ArrayList<String>(
            Arrays.asList("list", "mark", "unmark", "todo", "deadline", "event", "delete"));
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public CYbot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (MyException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.welcomeMsg();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printHorizontalLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (MyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printHorizontalLine();
            }
        }
    }

    /*
    private static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    private static void welcomeMsg() {
        printHorizontalLine();
        System.out.println("Hello! I'm CYbot.");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    private static void byeMsg() {
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        taskList.printTaskList();
    }

    */


    /*
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
    */


    /*
    private static LocalDateTime parseDateTime(String dateTimeStr) throws IllegalArgumentException {
        try {
            DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm");
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Invalid datetime format. Format: YYYY-MM-DD HH:mm\n" +
                            "Example: 2026-02-18 15:26"
            );
        }
    }

     */

    /*
    private static void mark(int index) {
        try {
            taskList.get(index).markDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(taskList.get(index));
            saveToFile();
        } catch (MyException e) {
            System.out.println("Index invalid: " + e);
        }
    }

    private static void unmark(int index) {
        try {
            taskList.get(index).unmarkDone();
            System.out.println("Ok, I've marked this task as not done yet:");
            System.out.println(taskList.get(index));
            saveToFile();
        } catch (MyException e) {
            System.out.println("Index invalid: " + e);
        }

    }

    private static void printNumTask() {
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
    }

    private static void addTask(Task task) {
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        printNumTask();
        saveToFile();
    }

    /**
     * Ask Storage to save tasks to file (hard disk)

    private static void saveToFile() {
        try {
            storage.save(taskList);
        } catch (MyException e) {
            System.out.println("Warning: Could not save tasks: " + e.getMessage());
        }
    }


    private static void printDateFormat() {
        System.out.println("[date] format: " + Task.fileDateFormatStr());
    }

    */
    /*
    private static void todo(String command) {
        try {
            if (command.length() < 5) {
                throw new IllegalArgumentException("No todo task is given.");
            }
            String name = command.substring(5);
            Task newTask = new Todo(name);
            addTask(newTask);
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println("What is your todo task?");
        }
    }

    private static void deadline(String command) {
        try {
            if (command.length() < 9) {
                throw new IllegalArgumentException("No \"deadline\" task is given.");
            }
            int byIndex = command.indexOf(" /by ");

            String name = command.substring(9, byIndex);
            String by = command.substring(byIndex + 5).trim();

            LocalDateTime byDateTime = Task.parseFileDateTime(by);
            Task newTask = new Deadline(name, byDateTime);
            addTask(newTask);
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println("What is your deadline task?");
            System.out.println("Format: deadline [task name] /by [date]");
            printDateFormat();
        }
    }


    private static void event(String command) {
        try {
            if (command.length() < 9) {
                throw new IllegalArgumentException("No \"deadline\" task is given.");
            }

            int fromIndex = command.indexOf(" /from ");
            int toIndex = command.indexOf(" /to ");

            String name = command.substring(6, fromIndex);

            String from = command.substring(fromIndex + 7, toIndex).trim();
            String to = command.substring(toIndex + 5).trim();

            LocalDateTime fromDateTime = Task.parseFileDateTime(from);
            LocalDateTime toDateTime = Task.parseFileDateTime(to);

            if (fromDateTime.isAfter(toDateTime)) {
                throw new IllegalArgumentException("Start time cannot be after End time.");
            }

            Task newTask = new Event(name, fromDateTime, toDateTime);
            addTask(newTask);

        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println(e);
            System.out.println("What is your event task?");
            System.out.println("Format: event [task name] /from [date] /to [date]");
            printDateFormat();

        }
    }
    */

    /*
    private static void delete(int index) {
        try {
            Task removedTask = taskList.delete(index);
            System.out.println("Noted. I've removed this task: ");
            System.out.println(removedTask);
            printNumTask();
            saveToFile();
        } catch (MyException e) {
            System.out.println("Index invalid: " + e);
        }
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
                todo(userInput);
                break;
            case "deadline":
                deadline(userInput);
                break;
            case "event":
                event(userInput);
                break;
            case "delete":
                delete(Integer.parseInt(wholeCmd[1]) - 1);
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

            try {
                if (isCommand(userInput)) {
                    callCommand(userInput);
                } else {
                    throw new IllegalArgumentException("wrong commadn!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Sorry, I dont know what that means");
            }
            printHorizontalLine();
        }
    }

    */
    public static void main(String[] args) {
        new CYbot(PATH_FILE).run();

    }


}
