package cybot;

import cybot.command.*;
import cybot.task.TaskList;
import cybot.task.Task;
import cybot.task.Deadline;
import cybot.task.Event;
import cybot.task.Todo;

import java.util.Scanner;

/**
 * Parses user input strings into executable Command obj
 * handles all command recognition and validation
 */
public class Parser {

    private static String[] breakInput(String command) {
        return command.split("\\s+");
    }

    private static void checkInputLen(String[] input, int length, String msg) throws MyException {
        if (input.length < length) {
            throw new MyException(msg);
        }
    }
    private static void checkInputStyle(String[] input, String msg) throws MyException {
        if (input.length < 2) {
            throw new MyException(msg);
        }
        try {
            Integer.parseInt(input[1]);
        } catch (NumberFormatException e) {
            throw new MyException(msg);
        }
    }

    /**
     * Parses input string and returns corresponding Command
     * Supports commands: todo, deadline, event, list, find, mark, unmark, delete, bye
     * @param input user input string
     * @return corresponding Command
     * @throws MyException for invalid input
     */
    public static Command parse(String input) throws MyException {
        String[] wholeCmd = breakInput(input);
        String command = wholeCmd[0].toLowerCase();

        switch (command) {
        case "list":
            return new ListCommand();
        case "mark":
            checkInputStyle(wholeCmd, "Please specify a task number to mark.");
            return new MarkCommand(Integer.parseInt(wholeCmd[1]), true);
        case "unmark":
            checkInputStyle(wholeCmd, "Please specify a task number to unmark.");
            return new MarkCommand(Integer.parseInt(wholeCmd[1]), false);

        case "todo":
            checkInputLen(wholeCmd, 2, "Please name the Todo task.");
            return new AddCommand(new Todo(input.substring(5)));
        case "deadline":
            checkInputLen(wholeCmd, 3, "Please use: " + Deadline.DEADLINE_FORMAT);
            return new AddCommand(Deadline.createDeadline(input.substring(9)));
        case "event":
            checkInputLen(wholeCmd, 4, "Please use: " + Event.EVENT_FORMAT);
            return new AddCommand(Event.createDeadline(input.substring(6)));
        case "delete":
            checkInputStyle(wholeCmd, "Please specify a task number to delete.");
            return new DeleteCommand(Integer.parseInt(wholeCmd[1]));
        case "bye":
            return new ExitCommand();
        case "find":
            checkInputLen(wholeCmd, 2, "Please input keyword to find");
            return new FindCommand(input.substring(5));

        default:
            throw new MyException("Invalid expression");
        }

    }




}
