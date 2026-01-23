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

    public static void main(String[] args) {
    welcomeMsg();
    byeMsg();
    }
}
