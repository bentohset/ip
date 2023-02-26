package duke.exceptions;
public class InvalidFormatException extends DukeException {
    private static final String MESSAGE = "Wrong format! Please try again.";

    public InvalidFormatException(String... missing) {
        super("Wrong format! Please include " + String.join(" and ", missing));
    }

    public InvalidFormatException() {
        super(MESSAGE);
    }
}
