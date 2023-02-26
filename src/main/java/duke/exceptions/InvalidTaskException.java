package duke.exceptions;

/**
 * An exception representing an input string which cannot
 * be parsed into a valid Task
 */
public class InvalidTaskException extends DukeException {
    public InvalidTaskException(String task) {
        super("The description of a " + task + " cannot be empty.");
    }
}
