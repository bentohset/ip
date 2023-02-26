package duke.exceptions;

/**
 * An exception representing an input string which is
 * given in the wrong format and missing required props of /by or /from or /to
 */
public class InvalidFormatException extends DukeException {
    private static final String MESSAGE = "Wrong format! Please try again.";

    public InvalidFormatException(String... missing) {
        super("Wrong format! Please include " + String.join(" and ", missing));
    }

    public InvalidFormatException() {
        super(MESSAGE);
    }
}
