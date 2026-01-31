package cybot;

import cybot.command.Command;
import cybot.task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Main class for CYbot
 * a Personal Assistant Chatbot to manage tasks
 */
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

    /**
     * @param filePath path to the file where tasks are saved
     */
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

    /**
     * Starts the chatbot
     */
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

    /**
     * main entry point
     * @param args
     */
    public static void main(String[] args) {
        new CYbot(PATH_FILE).run();

    }


}
