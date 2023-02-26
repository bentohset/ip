package duke.exceptions;
public class InvalidTaskException extends DukeException {
    public InvalidTaskException(String task) {
        super("The description of a " + task + " cannot be empty.");
    }
}
