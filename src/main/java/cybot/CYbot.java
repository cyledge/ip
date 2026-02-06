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


    private static final String FILE_PATH = "./data/cybot.txt";
    private static ArrayList<String> cmdList = new ArrayList<String>(
            Arrays.asList("list", "mark", "unmark", "todo", "deadline", "event", "delete"));
    private Storage storage;
    private TaskList tasks;






    @Override
    public void start(Stage stage) {
        try {
            storage = new Storage(FILE_PATH);
            try {
                tasks = storage.load();
            } catch (MyException e) {
                // To Add: ui.loadingerror() ?
                tasks = new TaskList();
            }

            // load FXML
            FXMLLoader fxmlLoader = new FXMLLoader(CYbot.class.getResource("/view/MainWindow.fxml"));
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
            String response = c.execute(tasks, storage);
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
    }
}
