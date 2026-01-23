import java.util.ArrayList;
import java.util.Scanner;

public class CYbot {
    private static String myName = "CYbot";
    private static ArrayList<String> strList = new ArrayList<String>();

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
        for (int i = 1; i <= strList.toArray().length; i++) {
            System.out.println(i + ". " + strList.get(i-1));
        }
    }

    private static void echoMsg(Scanner scanner) {
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            printHorizontalLine();
            if (userInput.equals("list")) {
                printList();
                continue;
            }
            strList.add(userInput);
            System.out.println("added: " + userInput);
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
