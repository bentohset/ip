package duke.exceptions;
public class InvalidCommandException extends DukeException {
    private static final String MESSAGE = "I'm sorry, but I don't know what that means";

    public InvalidCommandException(String message) {
        super(message);
    }

    public InvalidCommandException() {
        super(MESSAGE);
    }
}
