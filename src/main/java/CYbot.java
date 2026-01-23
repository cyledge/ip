import java.util.Scanner;

public class CYbot {
    private static String myName = "CYbot";

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

    private static void echoMsg(Scanner scanner) {
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            printHorizontalLine();
            String response = userInput;
            System.out.println(response);
            printHorizontalLine();
        }
    }

    public static void main(String[] args) {
    welcomeMsg();
    Scanner scanner = new Scanner(System.in);
    echoMsg(scanner);

    byeMsg();

    }
}
