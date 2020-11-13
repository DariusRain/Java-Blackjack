package blackjack.utils.UserInteractions;
import java.util.Scanner;

// In charge of inputting & outputting to the user.
public class Console {

    // Every instantiated class will use this variable for input
    private static Scanner input = new Scanner(System.in);

    // Every instantiated class will use this variable for output
    // --> output will keep concatenating previous elements with hyphens
    public static String output;

    // Clears the output
    public static void clear() {
        output = "";
    }

    public static void clearScreen() {
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.flush();
    }

    public static void out(String s) {
        output += s;
    }

    public static void log(String s) {
        System.out.println(s);
        output += s + "-";
    }

    public static void logf(String s) {
        System.out.print(s);
        output += s + "-";
    }

    public static String input(String message) {
        System.out.print(message);
        return input.nextLine();
    }

    public static String input() {
        return input.nextLine();
    }

}