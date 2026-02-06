package cybot;

import cybot.command.Command;
import cybot.task.TaskList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
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
public class CYbot extends Application {
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

    public CYbot() {
        this(PATH_FILE);
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
                c.execute(tasks, storage, ui);
                isExit = c.isExit();
            } catch (MyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printHorizontalLine();
            }
        }
    }




    @Override
    public void start(Stage stage) {
        try {
            storage = new Storage(PATH_FILE);
            try {
                tasks = storage.load();
            } catch (MyException e) {
                // To Add: ui.loadingerror() ?
                tasks = new TaskList();
            }

            // load FXML
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            // set up controller, inject this instance
            fxmlLoader.<MainWindow>getController().setDuke(this);

            // configure stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Process user input and return response
     * @param input
     * @return
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(tasks, storage, ui);
            return response;
        } catch (MyException e) {
            return "Error: " + e.getMessage();
        }
    }
    /**
     * main entry point
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
