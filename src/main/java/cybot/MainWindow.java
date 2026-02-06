package cybot;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private CYbot cybot;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/trump.jpg"));
    private final Image botImage = new Image(this.getClass().getResourceAsStream("/images/biden.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }


    private void showWelcomeMsg() {
        String welcomeMsg = "Hello! I'm CYbot." + "\n" +"What can I do for you?";
        dialogContainer.getChildren().add(DialogBox.getBotDialog(welcomeMsg, botImage));
    }

    /** Injects the Duke instance */
    public void setDuke(CYbot cy) {
        cybot = cy;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.trim().isEmpty()) {
            return;
        }

        // add user dialog
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));

        String response = cybot.getResponse(input);
        dialogContainer.getChildren().add(
                DialogBox.getBotDialog(response, botImage)
        );
        userInput.clear();

        if (input.equals("bye")) {
            javafx.application.Platform.exit();
        }
    }
}

