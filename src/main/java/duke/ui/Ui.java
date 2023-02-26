package duke.ui;

public class Ui {
    public static final String LOGO = "\t ____        _        \n"
            + "\t|  _ \\ _   _| | _____ \n"
            + "\t| | | | | | | |/ / _ \\\n"
            + "\t| |_| | |_| |   <  __/\n"
            + "\t|____/ \\__,_|_|\\_\\___|\n";

    public static void printDivider() {
        System.out.println("\t____________________________________");
    }

    public static void printWelcome() {
        printDivider();
        System.out.println(LOGO);
        System.out.println("\tHello! I'm Duke\n"
                + "\tWhat can I do for you?");
        printDivider();
    }

    public static void printExit() {
        printDivider();
        System.out.println("\tSaving tasks... Hope to see you again soon!");
        printDivider();
    }

    public static void printMessage(String[] messages) {
        printDivider();
        for (String message : messages) {
            System.out.println(message);
        }
        printDivider();
    }
}
